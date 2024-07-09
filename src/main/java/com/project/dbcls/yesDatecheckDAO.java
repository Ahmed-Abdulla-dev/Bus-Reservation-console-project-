package com.project.dbcls;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

public class yesDatecheckDAO {
    public static LocalDate getYesDate() throws SQLException {
        String placequery = "select y_date from date_y";
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery(placequery);
        rs.next();
        Date SQLData = rs.getDate(1);
        LocalDate yesDate = SQLData.toLocalDate();
        return yesDate;
    }

    public static void setYesDate() throws SQLException {
        String placequery = "INSERT into DATE_Y VALUES (To_Date(SYSDATE,'dd-MM-yyyy'))";
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate(placequery);
    }

    public static void removeYesDate() throws SQLException {
        String placequery = "delete from date_y";
        Connection con = DbCon.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate(placequery);
    }
}
