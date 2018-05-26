package fr.acos.demolistviewwithkotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
/**
* Classe BO permettant de stocker les données à afficher
*/
data class Personne(val id: Long, val nom: String, val prenom: String)

/**
 * Classe adapter permettant de charger les données dans la liste
 */
class PersonneAdapter(context: Context?, resource: Int, objects: MutableList<Personne>?) : ArrayAdapter<Personne>(context, resource, objects)
{
    //Variable permettant de stocker l'identifiant du layout de style d'une ligne de liste
    private val ma_ligne:Int;

    //Au moment de l'initialisation de l'objet adapter on récupère l'identifiant du layout passé en paramètre
    //pour le fournir à la variable ma_ligne.
    init
    {
        ma_ligne = resource;
    }

    /**
     * Fonction appelée autant de fois qu'il y a d'objet à afficher dans la liste.
     *
     * @param position index de l'élément de la liste à afficher
     * @param convertView Vue représentant le layout style_dune_ligne.xml
     * @param parent Parent de la liste
     */
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View
    {
        //Au premier appel on charge le fichier style_dune_ligne.xml après on réutilise convertView
        val uneLigneView = convertView ?:  LayoutInflater.from(parent!!.getContext()).inflate(ma_ligne, parent, false)

        //Ces variables représente les TextView de la ligne à créer.
        val tvNom = uneLigneView.findViewById<TextView>(R.id.tv_nom)
        val tvPrenom = uneLigneView.findViewById<TextView>(R.id.tv_prenom)

        //Récupère les données à afficher.
        val personne = getItem(position)

        //Met les données dans les TextView
        tvNom.text = personne.nom
        tvPrenom.text = personne.prenom

        //La ligne est créée, on la retourne.
        return uneLigneView
    }
}

/**
 * Activité qui affiche la ListView.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bouchon
        val p1 = Personne(1,"Adnet","Romain")
        val p2 = Personne(2,"Roussel","Guenole")
        val p3 = Personne(3,"Baudouin","Vincent")
        val personnes = mutableListOf(p1,p2,p3)

        //Création de l'adapter
        val adapter = PersonneAdapter(this,R.layout.style_dune_ligne,personnes)

        //On lie l'adapter à la ListView
        la_liste_view.adapter = adapter
    }
}
