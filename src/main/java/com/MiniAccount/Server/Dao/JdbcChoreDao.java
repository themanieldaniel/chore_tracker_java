package com.MiniAccount.Server.Dao;

import com.MiniAccount.Server.Models.Chore;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;

import java.util.ArrayList;
import java.util.List;

public class JdbcChoreDao implements ChoreDao {
    public static final String CHORE_SELECT = "SELECT id, description, amount, completed, dateCompleted FROM chores";
    private final JdbcTemplate jdbcTemplate;

    public JdbcChoreDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Chore> getAllChores() {
        List<Chore> chores = new ArrayList<>();
        String sql = CHORE_SELECT;

        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql);
            while (rs.next()) {
                chores.add(mapRowToChore(rs));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chores;
    }

    @Override
    public Chore getChoreById(Long id) {
        Chore chore = null;
        String sql = CHORE_SELECT + " WHERE id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, id);
            if (rs.next()) {
                chore = mapRowToChore(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return chore;
    }

    @Override
    public Chore addChore(Chore chore) {
        Chore returnedChore = null;
        String sql = "INSERT INTO chores (description, amount, completed, dateCompleted) " +
                "VALUES (?, ?, ?, ?)";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, chore.getDescription(),
                    chore.getAmount(), chore.getCompleted(), chore.getDateCompleted());
            if (rs.next()) {
                returnedChore = mapRowToChore(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return returnedChore;
    }

    @Override
    public Chore updateChore(Chore chore) {
        Chore updatedChore = null;
        String sql = "UPDATE chores SET description = ?, amount = ?, " +
                "completed = ?, dateCompleted = ? WHERE id = ?";
        try {
            SqlRowSet rs = jdbcTemplate.queryForRowSet(sql, chore.getDescription(),
                    chore.getAmount(), chore.getCompleted(), chore.getDateCompleted(), chore.getId());
            if (rs.next()) {
                updatedChore = mapRowToChore(rs);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedChore;
    }

    @Override
    public int deleteChoreById(Long id) {
        int numberOfRowsDeleted = 0;
        String sql = "DELETE FROM chores WHERE id = ?";
        try {
            numberOfRowsDeleted = jdbcTemplate.update(sql, id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (numberOfRowsDeleted == 0) {
            throw new RuntimeException("Chore not found");
        }
        return numberOfRowsDeleted;
    }

    private Chore mapRowToChore(SqlRowSet rs) {
        Chore chore = new Chore();
        chore.setId(rs.getLong("id"));
        chore.setDescription(rs.getString("description"));
        chore.setAmount(rs.getDouble("amount"));
        chore.setCompleted(rs.getBoolean("completed"));
        chore.setDateCompleted(rs.getDate("dateCompleted").toLocalDate());
        return chore;
    }
}
