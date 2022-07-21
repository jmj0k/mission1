package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HistoryDAO;
import DAO.WifiDAO;
import DTO.HistoryDTO;
import DTO.WifiDTO;

/**
 * Servlet implementation class WifiController
 */
@WebServlet("/wifi/*")
public class WifiController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WifiController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		// TODO Auto-generated method stub
		 WifiDAO wifiDAO = new WifiDAO();
		 HistoryDAO historyDAO = new HistoryDAO();
		 
		 String[] uri = req.getRequestURI().split("/wifi");
		 String context = req.getContextPath();
		 String command = uri.length < 2 ? uri[0] : uri[1];
		 String path = "/";
		 
		 if (command.equals("/history")) {
			 path = "/views/SearchHistory.jsp";
			 try {
				List<HistoryDTO> result = historyDAO.selectUserHistory();
				req.setAttribute("result", result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 } else if (command.equals("/main") || command.equals("/")) {
			 path = "/views/Search.jsp";
		 } else if (command.equals("/get_wifi_info")) {
			 path = "/views/load.jsp";
		 } else if (command.equals("/get_nearby_wifi")) {
			 path = "/views/Search.jsp";
			 String lat = req.getParameter("lat");
			 String lnt = req.getParameter("lnt");
			 try {
				 historyDAO.insertUserHistory(lat, lnt);
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			 try {
				List<WifiDTO> result = wifiDAO.selectNearbyWifi(Double.parseDouble(lat), Double.parseDouble(lnt));
				req.setAttribute("result", result);
			 } catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 } else if (command.equals("/delete_history")) {
			 path = "/views/SearchHistory.jsp";
			 String idx = req.getParameter("idx");
			 
			 try {
				historyDAO.deleteUserHistory(idx);
				List<HistoryDTO> result = historyDAO.selectUserHistory();
				req.setAttribute("result", result);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		 }
		 RequestDispatcher dispatcher = req.getRequestDispatcher(path);
		 dispatcher.forward(req, res);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

	
}

