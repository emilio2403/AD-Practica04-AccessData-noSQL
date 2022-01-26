package repository;

import com.mongodb.client.MongoCollection;
import com.mongodb.client.model.FindOneAndReplaceOptions;
import com.mongodb.client.model.ReturnDocument;
import dao.Login;
import manager.MongoDBController;
import org.bson.Document;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.mongodb.client.model.Filters.eq;

public class RepoLogin implements CrudRepository<Login, Long> {
    @Override
    public Optional<List<Login>> getAll() throws SQLException {
        MongoDBController mongoController = MongoDBController.getInstance();
        mongoController.open();
        MongoCollection<Login> loginCollection = mongoController.getCollection("mongodb", "login", Login.class);
        List<Login> list = loginCollection.find().into(new ArrayList<>());
        mongoController.close();
        return Optional.of(list);
    }

    @Override
    public Optional<Login> getById(Long id) throws SQLException {
        MongoDBController mongoController = MongoDBController.getInstance();
        mongoController.open();
        MongoCollection<Login> userCollection = mongoController.getCollection("mongodb", "login", Login.class);
        Login login = userCollection.find(eq("_id", id)).first();
        mongoController.close();
        if (login != null)
            return Optional.of(login);
        throw new SQLException("Error LoginRepository dont exist login with ID: " + id);
    }

    @Override
    public Optional<Login> save(Login login) throws SQLException {
        MongoDBController mongoController = MongoDBController.getInstance();
        mongoController.open();
        MongoCollection<Login> loginCollection = mongoController.getCollection("mongodb", "login", Login.class);
        try {
            loginCollection.insertOne(login);
            return Optional.of(login);
        } catch (Exception e) {
            throw new SQLException("Error LoginRepository to insert login into database: " + e.getMessage());
        } finally {
            mongoController.close();
        }
    }

    @Override
    public Optional<Login> update(Login login) throws SQLException {
        MongoDBController mongoController = MongoDBController.getInstance();
        mongoController.open();
        MongoCollection<Login> userCollection = mongoController.getCollection("mongodb", "login", Login.class);
        try {
            Document filtered = new Document("_id", login.getId());
            FindOneAndReplaceOptions returnDoc = new FindOneAndReplaceOptions().returnDocument(ReturnDocument.AFTER);
            return Optional.ofNullable(userCollection.findOneAndReplace(filtered, login, returnDoc));
        } catch (Exception e) {
            throw new SQLException("Error LoginRepository to update login with id: " + login.getId() + " " + e.getMessage());
        } finally {
            mongoController.close();
        }
    }

    @Override
    public Optional<Login> delete(Login login) throws SQLException {
        MongoDBController mongoController = MongoDBController.getInstance();
        mongoController.open();
        MongoCollection<Login> loginCollection = mongoController.getCollection("mongodb", "login", Login.class);
        try {
            Document filtered = new Document("_id", login.getId());
            return Optional.ofNullable(loginCollection.findOneAndDelete(filtered));
        } catch (Exception e) {
            throw new SQLException("Error UserRepository to delete user with id: " + login.getId() + " " + e.getMessage());
        } finally {
            mongoController.close();
        }
    }
}
