package ru.edu.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.edu.error.CustomException;
import ru.edu.error.Errors;
import ru.edu.service.CityInfo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.util.Objects.isNull;

@Component
public class CityRepository {

    private Connection connection;


    @Autowired
    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    /**
     * Get all.
     *
     * @author -  Филатов Руслан
     */
    public List<CityInfo> getAll() {
        ArrayList<CityInfo> cities = new ArrayList<>();

        try (Statement statement = connection.createStatement()) {
            String sql = "select * from cities";

            try (ResultSet result = statement.executeQuery(sql)) {
                while(result.next()) {
                    CityInfo city = new CityInfo();
                    city.setId(result.getString("id"));
                    city.setName(result.getString("name"));
                    city.setDescription(result.getString("description"));
                    city.setClimate(result.getString("climate"));
                    city.setPopulation(result.getInt("population"));
                    city.setLatitude(result.getDouble("latitude"));
                    city.setLongitude(result.getDouble("longitude"));
                    cities.add(city);
                }
            }
        } catch (Exception ex) {
            throw new CustomException("Failed to getAll ", ex, Errors.SQL_EXCEPTION);
        }

        return cities;
    }

    /**
     * Get city by id. Returns null if not found.
     *
     * @param cityId - item id
     * @author - Руслан Сейфетдинов
     */
    public CityInfo getCity(String cityId) {
        // System.out.println("CityRepository.getCity started");

        String sql = "SELECT * FROM cities WHERE id = ?";

        CityInfo cityInfo = new CityInfo();

        try(PreparedStatement statement = connection.prepareStatement(sql)){
            statement.setString(1, cityId);

            try(ResultSet resultSet = statement.executeQuery()){
                if(resultSet.next() == false){
                    return null;
                }

                String id = resultSet.getString("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                String climate = resultSet.getString("climate");
                int population = resultSet.getInt("population");
                double latitude = resultSet.getDouble("latitude");
                double longitude = resultSet.getDouble("longitude");

                cityInfo.setId(id);
                cityInfo.setName(name);
                cityInfo.setDescription(description);
                cityInfo.setClimate(climate);
                cityInfo.setPopulation(population);
                cityInfo.setLatitude(latitude);
                cityInfo.setLongitude(longitude);

                // System.out.println("CityRepository.getCity finished");

                return cityInfo;
            }
        }catch(Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    /**
     * Create new city.
     *
     * @author - Борисов Захар
     */
    public CityInfo create(CityInfo info) {

        String sql = "INSERT INTO cities (name, description, climate, population, latitude, longitude) " +
                "VALUES (?, ?, ?, ?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, info.getName());
            statement.setString(2, info.getDescription());
            statement.setString(3, info.getClimate());
            statement.setString(4, String.valueOf(info.getPopulation()));
            statement.setString(5, String.valueOf(info.getLatitude()));
            statement.setString(6, String.valueOf(info.getLongitude()));

            statement.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return info;
    }

    /**
     * Update existing city. Don't change id
     *
     * @author - Соловьев Александр
     */
    public CityInfo update(CityInfo info) {

        String sql;

        if ( isNull( getCity( info.getId() ) )) {
            throw new CustomException("City "+ info.getName() +" not found in database ERROR", Errors.CITY_NOT_FOUND);
        } else {

            sql = "UPDATE cities SET name = ?, description = ?, climate = ?, population = ?, " +
                    "latitude = ?, longitude = ? WHERE id = ? ";
        }

        try (PreparedStatement statement = connection.prepareStatement(sql)) {

            statement.setString(1, info.getName());
            statement.setString(2, info.getDescription());
            statement.setString(3, info.getClimate());
            statement.setString(4, String.valueOf(info.getPopulation()));
            statement.setString(5, String.valueOf(info.getLatitude()));
            statement.setString(6, String.valueOf(info.getLongitude()));
            statement.setString(7, String.valueOf(info.getId()));

            statement.executeUpdate();

        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new CustomException("Failed to update city " + info, Errors.UPDATE_ERROR);
        }

        return info;
    }

    /**
     * Delete city by id.
     *
     * @author - Вдовыко Алексей
     */
    public CityInfo delete(String cityId) {

        CityInfo info;

        try (PreparedStatement statement = connection.prepareStatement("delete from cities where id = ?")) {

            info = getCity(cityId);
            statement.setObject(1, cityId);
            int affectedRows = statement.executeUpdate();
            System.out.println("Удалено строк = " + affectedRows);

        } catch (Exception ex) {
            throw new CustomException("Ошибка, город с cityId " + cityId + " не удален" , Errors.DELETE_ERROR);
        }

        return info;
    }
}
