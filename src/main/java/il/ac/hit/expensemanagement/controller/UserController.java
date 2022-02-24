package il.ac.hit.expensemanagement.controller;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import il.ac.hit.expensemanagement.model.*;


public class UserController extends AbstractController {

    public UserController(IModelExpenseDAO dao) {
        super(dao);
    }

    public void add(HttpServletRequest request, HttpServletResponse response) {
        String description = request.getParameter("description");
        String category = request.getParameter("category");
        String sumStr = request.getParameter("sum");
        String dateStr = request.getParameter("date");
        String user = (String) request.getSession().getAttribute("user");
        double sum;
        Date date;

        if (description != null || category != null || sumStr != null || dateStr != null) {
            description = description.trim();
            dateStr = dateStr.trim();
            category = category.trim();
            sumStr = sumStr.trim();
            if (description != "" || category != "" || sumStr != "" || dateStr != "") {
                try {
                    sum = Double.parseDouble(sumStr);
                    date = Date.valueOf(dateStr);
                    dao.addExpenseItem(new ExpenseItem(sum, category, description, date, user));
                    request.setAttribute("mes", "add successfully");

                } catch (NumberFormatException e) {
                    e.printStackTrace();
                } catch (ExpenseManagerException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("mes", "Error in input");

            }
        }

    }

    public void items(HttpServletRequest request, HttpServletResponse response) {
        try {
            String user = (String) request.getSession().getAttribute("user");
            if (user == null) {
                request.getRequestDispatcher(Utils.file + "login.jsp").forward(request, response);
            } else {
                List<?> data = dao.getAllExpenses(user).getListItem();
                request.setAttribute("data", data);
            }

        } catch (ExpenseManagerException e) {
            e.printStackTrace();
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void login(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if (userName != null || password != null) {
            try {
                User user = new User(userName, password);
                if (dao.login(user) == true) {
                    request.getSession().setAttribute("user", userName);
                } else {
                    request.setAttribute("mes", "login did not succeed");
                }

            } catch (ExpenseManagerException e) {
                e.printStackTrace();
            }
        }

    }

    public void registration(HttpServletRequest request, HttpServletResponse response) {
        String userName = request.getParameter("username");
        String password = request.getParameter("password");

        if (userName != null || password != null) {
            userName = userName.trim();
            password = password.trim();
            if (!userName.equals("") && !password.equals("")) {
                try {
                    User user = new User(userName, password);
                    if (dao.checkExistingUserName(userName) == true) {
                        request.setAttribute("mes", "Such a username already exists");
                    } else {
                        dao.addUser(user);
                        request.setAttribute("mes", "add user successfully username = " + userName);
                    }
                } catch (ExpenseManagerException e) {
                    e.printStackTrace();
                }
            } else {
                request.setAttribute("mes", "Error in registration Missing data");
            }
        }
    }

    public void home(HttpServletRequest request, HttpServletResponse response) {
    }

}
