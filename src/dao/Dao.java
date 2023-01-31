package dao;


import java.util.List;
import java.util.Optional;

//Dao - интерфейс с описанием общих методов, которые будут использоваться при взаимодействии с базой данных



public interface Dao<K, E> {  //K - ключом является Integer (id), E - entity(сущность)

    boolean delete(K id);  //удаление по указанному id

    E save(E entity); //добавление новой записи в БД

    void update(E entity);  //изменение записи в БД

    Optional<E> findById(K id);  //выбираем записи по id

    List<E> findAll();  //выбираем все записи из БД

}
