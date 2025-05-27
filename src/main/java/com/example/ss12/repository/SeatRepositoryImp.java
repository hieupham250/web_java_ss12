package com.example.ss12.repository;

import com.example.ss12.connection.ConnectionDB;
import com.example.ss12.model.Seat;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SeatRepositoryImp implements SeatRepository {
    @Override
    public List<Seat> findByBusId(int busId) {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Seat> seats = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call find_seat_by_bus_id(?)}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Seat seat = new Seat(
                        rs.getInt("id"),
                        rs.getString("name_seat"),
                        rs.getBigDecimal("price"),
                        rs.getInt("bus_id"),
                        rs.getString("status")
                );
                seats.add(seat);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return seats;
    }

    @Override
    public boolean save(Seat seat) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call save_seat(?,?,?,?)}");
            cstmt.setString(1, seat.getNameSeat());
            cstmt.setBigDecimal(2, seat.getPrice());
            cstmt.setInt(3, seat.getBusId());
            cstmt.setString(4, seat.getStatus());
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public boolean update(Seat seat) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call update_seat(?,?,?,?,?)}");
            cstmt.setInt(1, seat.getId());
            cstmt.setString(2, seat.getNameSeat());
            cstmt.setBigDecimal(3, seat.getPrice());
            cstmt.setInt(4, seat.getBusId());
            cstmt.setString(5, seat.getStatus());
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }

    @Override
    public boolean deleteByBusId(int busId) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call delete_seat_by_bus_id(?)}");
            cstmt.setInt(1, busId);
            cstmt.executeUpdate();
            result = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return result;
    }
}
