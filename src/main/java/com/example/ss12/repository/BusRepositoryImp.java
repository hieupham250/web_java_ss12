package com.example.ss12.repository;

import com.example.ss12.connection.ConnectionDB;
import com.example.ss12.model.Bus;
import org.springframework.stereotype.Repository;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

@Repository
public class BusRepositoryImp implements BusRepository {
    @Override
    public List<Bus> findAll() {
        Connection conn = null;
        CallableStatement cstmt = null;
        List<Bus> buses = new ArrayList<>();
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call find_all_bus()}");
            ResultSet rs = cstmt.executeQuery();
            while (rs.next()) {
                Bus bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("license_plate"),
                        rs.getString("bus_type"),
                        rs.getInt("row_seat"),
                        rs.getInt("col_seat"),
                        rs.getInt("total_seat"),
                        rs.getString("image")
                );
                buses.add(bus);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return buses;
    }

    @Override
    public Bus findById(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        Bus bus = null;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call find_all_bus(?)}");
            cstmt.setInt(1, id);
            ResultSet rs = cstmt.executeQuery();
            if (rs.next()) {
                bus = new Bus(
                        rs.getInt("id"),
                        rs.getString("license_plate"),
                        rs.getString("bus_type"),
                        rs.getInt("row_seat"),
                        rs.getInt("col_seat"),
                        rs.getInt("total_seat"),
                        rs.getString("image")
                );
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            ConnectionDB.closeConnection(conn, cstmt);
        }
        return bus;
    }

    @Override
    public boolean create(Bus bus) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call create_bus(?, ?, ?, ?, ?, ?)}");
            cstmt.setString(1, bus.getLicensePlate());
            cstmt.setString(2, bus.getBusType());
            cstmt.setInt(3, bus.getRowSeat());
            cstmt.setInt(4, bus.getColSeat());
            cstmt.setInt(5, bus.getTotalSeat());
            cstmt.setString(6, bus.getImage());
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
    public boolean update(Bus bus) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call create_bus(?, ?, ?, ?, ?, ?, ?)}");
            cstmt.setInt(1, bus.getId());
            cstmt.setString(2, bus.getLicensePlate());
            cstmt.setString(3, bus.getBusType());
            cstmt.setInt(4, bus.getRowSeat());
            cstmt.setInt(5, bus.getColSeat());
            cstmt.setInt(6, bus.getTotalSeat());
            cstmt.setString(7, bus.getImage());
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
    public boolean delete(int id) {
        Connection conn = null;
        CallableStatement cstmt = null;
        boolean result = false;
        try {
            conn = ConnectionDB.openConnection();
            cstmt = conn.prepareCall("{call create_bus(?)}");
            cstmt.setInt(1, id);
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
