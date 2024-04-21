package br.com.codandosimples.dao;

import br.com.codandosimples.infra.ConnectionFactory;
import br.com.codandosimples.model.Cesupa;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CesupaDAO implements  ICesupaDAO{
    @Override
    public Cesupa save(Cesupa cesupa) {
       try(Connection connection = ConnectionFactory.getConnection()) {
           String sql = "INSERT INTO Cesupa(nome,descricao,data,npessoas) VALUES(?,?,?,?)";

           PreparedStatement preparedStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
           preparedStatement.setString(1,cesupa.getNome());
           preparedStatement.setString(2, cesupa.getDescricao());
           preparedStatement.setDate(3, java.sql.Date.valueOf(cesupa.getData()));
           preparedStatement.setInt(4, cesupa.getNpessoas());

           preparedStatement.executeUpdate();

           ResultSet resultSet = preparedStatement.getGeneratedKeys();
           resultSet.next();

           Long generatedId = resultSet.getLong("id");
           cesupa.setId(generatedId);

       } catch (SQLException ex) {
           throw new RuntimeException(ex);
       }

        return cesupa;
    }

    @Override
    public Cesupa update(Cesupa cesupa) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            String sql = "UPDATE  Cesupa SET nome = ?,descricao = ?,data = ?,npessoas = ? WHERE id = ?;";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1,cesupa.getNome());
            preparedStatement.setString(2, cesupa.getDescricao());
            preparedStatement.setDate(3, java.sql.Date.valueOf(cesupa.getData()));
            preparedStatement.setInt(4, cesupa.getNpessoas());
            preparedStatement.setLong(5,cesupa.getId());

            preparedStatement.executeUpdate();



        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return cesupa;
    }

    @Override
    public void delete(long id) {
        try(Connection connection = ConnectionFactory.getConnection()) {
            String sql = "DELETE FROM Cesupa WHERE id = ?";

            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);

            preparedStatement.executeUpdate();



        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

    }

    @Override
    public List<Cesupa> findAll() {
        String sql = "SELECT id,nome,descricao,data,npessoas FROM Cesupa";

        List<Cesupa> cesupa = new ArrayList<>();

        try(Connection connection = ConnectionFactory .getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long id = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                int npessoas = rs.getInt("npessoas");

                Cesupa cesupa1 = new Cesupa(id, nome, descricao, data, npessoas);
                cesupa.add(cesupa1);
            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return cesupa;
    }

    @Override
    public Optional<Cesupa> findById(long id) {
        String sql = "SELECT id,nome,descricao,data,npessoas FROM Cesupa WHERE id = ?";


        Cesupa cesupa = null;
        try(Connection connection = ConnectionFactory .getConnection()) {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Long pKey = rs.getLong("id");
                String nome = rs.getString("nome");
                String descricao = rs.getString("descricao");
                LocalDate data = rs.getDate("data").toLocalDate();
                int npessoas = rs.getInt("npessoas");

                cesupa = new Cesupa(pKey, nome, descricao, data, npessoas);

            }

        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }
        return Optional.ofNullable(cesupa);
    }
}
