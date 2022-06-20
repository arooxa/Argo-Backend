package com.example.Argo.repos;

import com.example.Argo.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepo extends JpaRepository<Item, Integer> {

    public List<Item> findByItemgroupId(int groupId);
}
