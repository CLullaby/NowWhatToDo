package servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ActiviteModelBean;
import model.AvancementActiviteModelBean;
import dao.fabrique.DaoFabrique;
import dao.instance.DaoActivite;
import dao.instance.DaoAvancement;

/**
 * Servlet implementation class RecupererActiviteServlet
 */
@WebServlet("/RecupererActivite")
public class RecupererActiviteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private DaoActivite daoA;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecupererActiviteServlet() {
        super();
        daoA = DaoFabrique.getInstance().createActiviteDao();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//recuperation du parametre
		String domaine = request.getParameter("domaine");
		
		//declaration de listes
		ArrayList<ActiviteModelBean> allActList = daoA.getActivitebyDomaine(domaine); // passer en parametre le domaine
		ArrayList listeGeneral = new ArrayList();
		List<String> nomAct = new ArrayList();
		
		//initialisation de la liste en recuperant tout les nom d'activite dans le domaine recupere
		for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
		{
			ActiviteModelBean current = actIter.next();
			if(!nomAct.contains(current.getNomActivite()))
			{
				nomAct.add(current.getNomActivite());
				System.out.println(" nom d'act :" + current.getNomActivite() + " ");
			}
		}
		
		for(int i = 0; i< nomAct.size(); i++)
		{
			//chaque itteration correspond a une nouvelle activite
			ArrayList<ActiviteModelBean> liste  = new ArrayList<ActiviteModelBean>();
			for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
			{	
				//si le nom de l'activite courrante correspond au nom de l'activite de l'element courant
				//on ajoute le bean dans la liste courante 
				ActiviteModelBean current = actIter.next();
				if(current.getNomActivite().equals(nomAct.get(i)))
				{
					liste.add(current);
				}
			}
			//pour ne pas perdre les listes on les mets dans une liste de liste
			listeGeneral.add(liste);
		}
		
		/////////////////////////////////////////////////////////////////////
		//test 
		for( Iterator<ArrayList> listIter = listeGeneral.iterator(); listIter.hasNext();){
			for( Iterator<ActiviteModelBean> sublistIter = listIter.next().iterator(); sublistIter.hasNext();){
				
				System.out.println("List by act"+ sublistIter.next().getNomActivite() +" ");
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
