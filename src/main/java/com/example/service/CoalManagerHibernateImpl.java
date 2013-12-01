/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.service;

import com.example.domain.Coal;

import java.util.List;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aleksander
 */

@Component
@Transactional
public class CoalManagerHibernateImpl implements CoalManager {
    
    @Autowired
    private SessionFactory sessionFactory;
     
    @Override
    public void addCoal(Coal coal) {
        sessionFactory.getCurrentSession().persist(coal);
    }

    @Override
    public List<Coal> getAllCoal() {
        return sessionFactory.getCurrentSession().getNamedQuery("coal.all").list();
    }

    @Override
    public void deleteCoal(Coal coal) {
        sessionFactory.getCurrentSession().delete(coal);
    }

    @Override
    public Coal findCoalById(long id) {
        return (Coal) sessionFactory.getCurrentSession().getNamedQuery("coal.byId").setLong("id", id).uniqueResult();
    }

    @Override
    public void updateCoal(Coal coal) {
        sessionFactory.getCurrentSession().update(coal);
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
}
