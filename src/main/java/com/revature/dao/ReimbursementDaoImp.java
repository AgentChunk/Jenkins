package com.revature.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.util.ArrayList;
import java.util.List;

import com.revature.pojo.Employee;
import com.revature.pojo.ReimbursementForm;
import com.revature.util.ConnectionFactory;
import com.revature.util.LoggingUtil;

public class ReimbursementDaoImp implements ReimbursementDao {

	public List<ReimbursementForm> retreiveAllFormsOfEmployee(Employee e) {
		List<ReimbursementForm> rfList = new ArrayList<ReimbursementForm>();
		String sql = "SELECT * FROM REQUESTS WHERE E_ID = ?";
		
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ReimbursementForm rf = new ReimbursementForm();
				rf.setReqId(rs.getInt(1));
				if(rs.getInt(2)==0) {
					rf.setSupApproval(false);
				}else {
					rf.setSupApproval(true);
				}
				rf.setSupervisor(rs.getInt(3));
				if(rs.getInt(4)==0) {
					rf.setdHeadApproval(false);
				}else {
					rf.setdHeadApproval(true);
				}
				rf.setdHeadId(rs.getInt(5));
				if(rs.getInt(6)==0) {
					rf.setBcoApproval(false);
				}else {
					rf.setBcoApproval(true);
				}
				rf.setApprovedAmount(rs.getDouble(7));
				rf.setFormDate(rs.getDate(8));
				rf.setGrade(rs.getDouble(9));
				rf.setRequester(rs.getInt(10));
				rf.setReason(rs.getString(11));
				rf.setEventName(rs.getString(12));
				rf.setEventDate(rs.getTimestamp(13));
				rf.setCost(rs.getDouble(14));
				rf.setGradeId(rs.getInt(15));
				rf.setEventType(rs.getString(16));
				rf.setEventLocation(rs.getString(17));
				
				rfList.add(rf);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return rfList;
	}

	public void createRFormPrpStmt(ReimbursementForm r) {
		//create a new request and event if it doesn't exists
		int evId=0;
		PreparedStatement ps;
		Connection conn = null;
		Savepoint s = null;
		String sql = "INSERT INTO REQUESTS VALUES (1,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		
		
		try {
			conn = ConnectionFactory.getInstance().getConnection();
			conn.setAutoCommit(false);
			s = conn.setSavepoint("updateSavepoint");
			
			ps = ConnectionFactory.getInstance().getConnection().prepareStatement(sql);
			if(r.isSupApproval()) {
				ps.setInt(1, 1);
			}else {
				ps.setInt(1, 0);
			}
			ps.setInt(2, r.getSupervisor());
			if(r.isdHeadApproval()) {
				ps.setInt(3, 1);
			}else {
				ps.setInt(3, 0);
			}
			ps.setInt(4, r.getdHeadId());
			if(r.isBcoApproval()) {
				ps.setInt(5,1);
			}else {
				ps.setInt(5,0);
			}
			ps.setDouble(6, r.getApprovedAmount());
			ps.setDate(7, r.getFormDate());
			ps.setDouble(8, r.getGrade());
			ps.setInt(9, r.getRequester());
			ps.setString(10, r.getReason());
			ps.setString(11, r.getEventName());
			ps.setTimestamp(12, r.getEventDate());
			ps.setDouble(13, r.getCost());
			ps.setInt(14,r.getGradeId());
			ps.setString(15, r.getEventType());
			ps.setString(16, r.getEventLocation());
			
			ps.executeUpdate();
			LoggingUtil.logTrace("Reqeust created");
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}

	public void updateRForm(ReimbursementForm r) {
		String sql = "UPDATE REQUESTS SET SUP_APPROVAL=?, DHEAD_APPROVAL=?, BCO_APPROVAL=?, APROVED_AMOUNT=?, GRADE=?, REASON=? WHERE REQID=?";
		PreparedStatement ps;
		Connection conn = ConnectionFactory.getInstance().getConnection();;
		Savepoint s = null;
		
		try {
			conn.setAutoCommit(false);
			s = conn.setSavepoint("updateSavepoint");
			ps = conn.prepareStatement(sql);
			
			if(r.isSupApproval()) {
				ps.setInt(1, 1);
			}else {
				ps.setInt(1, 0);
			}
			if(r.isdHeadApproval()) {
				ps.setInt(2, 1);
			}else {
				ps.setInt(2, 0);
			}
			if(r.isBcoApproval()) {
				ps.setInt(3,1);
			}else {
				ps.setInt(3,0);
			}
			ps.setDouble(4, r.getApprovedAmount());
			ps.setDouble(5, r.getGrade());
			ps.setString(6, r.getReason());
			ps.setInt(7, r.getReqId());
			
			ps.executeUpdate();
			LoggingUtil.logTrace("Reqeust updated");
			
			conn.commit();
			conn.setAutoCommit(true);
			
		} catch (SQLException e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		}

	}
	
	//gets all the ReimbursementForm that an employee can approve
	public List<ReimbursementForm> retreiveFormsToApprove(Employee e) {
		String sql = "SELECT * FROM REQUESTS WHERE S_ID = ? OR DH_ID =?";
		List<ReimbursementForm> rfList = new ArrayList<ReimbursementForm>();
		
		int evId;
		
		try {
			Connection conn = ConnectionFactory.getInstance().getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, e.getId());
			ps.setInt(2, e.getId());
			ResultSet rs = ps.executeQuery();
			
			while(rs.next()) {
				ReimbursementForm rf = new ReimbursementForm();
				rf.setReqId(rs.getInt(1));
				if(rs.getInt(2)==0) {
					rf.setSupApproval(false);
				}else {
					rf.setSupApproval(true);
				}
				rf.setSupervisor(rs.getInt(3));
				if(rs.getInt(4)==0) {
					rf.setdHeadApproval(false);
				}else {
					rf.setdHeadApproval(true);
				}
				rf.setdHeadId(rs.getInt(5));
				if(rs.getInt(6)==0) {
					rf.setBcoApproval(false);
				}else {
					rf.setBcoApproval(true);
				}
				rf.setApprovedAmount(rs.getDouble(7));
				rf.setFormDate(rs.getDate(8));
				rf.setGrade(rs.getDouble(9));
				rf.setRequester(rs.getInt(10));
				rf.setReason(rs.getString(11));
				rf.setEventName(rs.getString(12));
				rf.setEventDate(rs.getTimestamp(13));
				rf.setCost(rs.getDouble(14));
				rf.setGradeId(rs.getInt(15));
				rf.setEventType(rs.getString(16));
				rf.setEventLocation(rs.getString(17));
				
				rfList.add(rf);
			}
			
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		return rfList;
	}

}
