package banip.action;

import javax.servlet.http.HttpServletRequest;

import banip.bean.CategoryBean;
import banip.data.BoardID;
import banip.data.BoardIDNull;
import banip.data.User;
import banip.sql.BoardDao;

public abstract class ActionBoard extends Action {
	/**
	 * 카테고리 키로 카테고리 bean 획득
	 * @param categoryID
	 * @return
	 */
	protected CategoryBean getCategoryBean(int categoryID) {
		// TODO Auto-generated method stub
		BoardDao dao = new BoardDao();
		CategoryBean bean = dao.getCategoryBean(categoryID);
		return bean;
	}
	
	/**
	 * board_id로 BoardID객체 획득
	 */
	protected BoardID getBoardID(HttpServletRequest request) {
		if(getString(request, "board_id") == null) return new BoardIDNull();
		int boardID = getInt(request, "board_id");
		BoardID id = new BoardID(boardID);
		return id;
	}


	protected boolean isCategoryNull(HttpServletRequest request) {
		// TODO Auto-generated method stub
		int categoryID = super.getInt(request, "category_id");
		return getCategoryBean(categoryID) == null;
	}

	protected boolean isBoardNull(HttpServletRequest request) {
		// TODO Auto-generated method stub
		return getBoardID(request).isNull();
	}

	public boolean isBoardWriten(User user, BoardID boardID) {
		// TODO Auto-generated method stub
		return boardID.getBean().getBOARD_USER_NAME().equals( user.getName() );
	}


}
