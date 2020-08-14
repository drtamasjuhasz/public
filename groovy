/** ChEMBL Searcher version 1.
* Uses the ChEMBL REST services to run a similarity search and pull back the number of molecules
* at least 95% similarity to the structure in that row and load into ChEMBL Sim field
*
* Usage: 
* Run the script, number of similar molecules are inserted into ChEMBL Sim fields of all lines
*
* @author Tamas Juhasz
*/

@Grab(group = 'org.codehaus.groovy.modules.http-builder', module = 'http-builder', version = '0.5.2')
import groovyx.net.http.RESTClient
import chemaxon.jep.Evaluator
import chemaxon.jep.ChemJEP
import chemaxon.jep.context.MolContext
import chemaxon.struc.Molecule

//to work with Entyt and its data provider, find the fields we need, create result set
def parent = dataTree.rootVertex.entity
def edp = parent.schema.dataProvider.getEntityDataProvider(parent)
def molFld = parent.fields.items.find { it.name == 'Structure' }
def fld = parent.fields.items.find { it.name == 'ChEMBL Sim Count' }
def rs = parent.schema.dataProvider.getDefaultResultSet(dataTree, false, DFEnvironmentRO.DEV_NULL)
def rootVS = rs.getVertexState(dataTree.rootVertex)

//Create the list of ids to work with, all or just the selected    
//List ids = rootVS.getSelectedRowsIds()
List ids = rootVS.getIds()

//loop for converting structure to cannonical SMILES, build query, count simmilar molecules and insert into fields

//structure to smiles
ids.each { id ->
    def getData = rootVS.getData([id], DFEnvironmentRO.DEV_NULL)
    def MarvinStructure mol =getData[id][molFld.id] 
    def Molecule cxnMol = mol.getNative() 
    String molSmiles = cxnMol.toFormat( "smiles:u" )
    println "Getting SMILES:"
    println molSmiles 
    
//build query if molecule is not CH4 or H+, since these two give exception   

if (molSmiles == ('C')|| molSmiles==('[H+]'))
{ println "SMILES of CdId #$id is invalid for the database"
  println ""
}

else
{
    def client = new RESTClient('https://www.ebi.ac.uk/chembl/api/data/')   //'https://www.ebi.ac.uk/chemblws/'  <- from sample script
    println "Performing ChEMBL query"
    def resp = client.get(path: "similarity/$molSmiles/95")   // "compounds/similarity/$query/$similarity"  <- from sample script
    println "Processing of query results..."

    if (resp.status == 200) 
 {
        println "ChEMBL response OK"
        def data = resp.data
        int count = 0
        
        //println data.molecules.molecule  <- for test purpose
        
        //loop to count all molecules 
        data.molecules.molecule.each { cpd ->
            count++           
        } 
        
        //perform update        
        def lock = edp.lockable.withLock( 'Updating' ){ envRW ->
        def vals = [:]
        vals[fld.id] = count
        print "Updating the number of similar molecules ($count) of CdId #"
        println id
        println""
        def resultSet = parent.schema.dataProvider.getDefaultResultSet(dataTree, false, DFEnvironmentRO.DEV_NULL)
        def vertexState = resultSet.getVertexState(dataTree.rootVertex)
        def ud = DFUpdateDescription.create(parent, id, vals)
        def submitList = Collections.singletonList(ud)
        edp.update(submitList, DFUndoConfig.OFF, envRW)
        }
  }     
     else 
  {
        // something went wrong with the web service call
        println "ERROR: code=$resp.status"
   }
}

}
