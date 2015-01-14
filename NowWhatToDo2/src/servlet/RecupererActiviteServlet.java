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
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RecupererActiviteServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String domaine = request.getParameter("domaine");
		
		DaoActivite daoA = DaoFabrique.getInstance().createActiviteDao();
		ArrayList<ActiviteModelBean> allActList = daoA.getActivitebyDomaine(domaine); // passer en parametre le domaine
		ArrayList listeGeneral = new ArrayList();
		List<String> nomAct = new ArrayList();
		
		for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
		{
			ActiviteModelBean current = actIter.next();
			if(!nomAct.contains(current.getNomActivite()))
			{
				nomAct.add(current.getNomActivite());
				System.out.println(" nom d'act :" + current.getNomActivite() + " ");
			}
		} //cree une liste avec tout les noms d'activites du domaine
		
		for(int i = 0; i< nomAct.size(); i++)
		{
			ArrayList<ActiviteModelBean> liste  = new ArrayList<ActiviteModelBean>();
			for (Iterator<ActiviteModelBean> actIter = allActList.iterator(); actIter.hasNext();)
			{		
				ActiviteModelBean current = actIter.next();
				if(current.getNomActivite().equals(nomAct.get(i)))
				{
					liste.add(current);
				}
			}
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
