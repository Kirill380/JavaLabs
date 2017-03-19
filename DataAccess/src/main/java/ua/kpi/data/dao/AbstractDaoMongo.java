package ua.kpi.data.dao;


import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static com.mongodb.client.model.Filters.eq;

public abstract class AbstractDaoMongo<T extends Identifiable<PK>, PK extends Integer> implements GenericDao<T, PK> {

    protected MongoDatabase dataBase;

    public AbstractDaoMongo(MongoDatabase dataBase) {
        this.dataBase = dataBase;
    }

    protected abstract MongoCollection<Document> getCollection();

    protected abstract List<T> convertDocumentsToObjects(List<Document> documents);

    protected abstract Document convertObjectToDocument(T object);

    protected abstract PK generateId(T object);

    @Override
    public T save(T object) throws PersistException {
        Document doc = convertObjectToDocument(object);
        PK id = generateId(object);
        doc.put("_id", id);
        try {
            getCollection().insertOne(doc);
        } catch (Exception ex) {
            throw new PersistException(ex);
        }
        return object;
    }

    @Override
    public T getById(PK key) throws PersistException {
        List<Document> documents = StreamSupport
                .stream(getCollection().find(eq("_id", key)).spliterator(), false)
                .collect(Collectors.toList());

        if (documents == null || documents.isEmpty()) {
            return null;
        }

        return convertDocumentsToObjects(documents).get(0);
    }

    @Override
    public void update(T item) throws PersistException {
        try {
            getCollection().updateOne(eq("_id", item.getId()), eq("$set", convertObjectToDocument(item)));
        } catch (Exception ex) {
            throw new PersistException(ex);
        }
    }

    @Override
    public void delete(T item) throws PersistException {
        try {
            getCollection().deleteOne(eq("_id", item.getId()));
        } catch (Exception ex) {
            throw new PersistException(ex);
        }
    }

    @Override
    public void deleteAll() throws PersistException  {
        try {
            getCollection().deleteMany(new Document());
        } catch (Exception ex) {
            throw new PersistException(ex);
        }

    }

    @Override
    public List<T> getAll() throws PersistException {
        List<Document> documents = StreamSupport
                .stream(getCollection().find().spliterator(), false)
                .collect(Collectors.toList());

        if (documents == null) {
            return new ArrayList<T>();
        }

        return convertDocumentsToObjects(documents);
    }
}
