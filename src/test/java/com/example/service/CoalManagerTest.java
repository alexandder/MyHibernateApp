/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.service;

import com.example.domain.Coal;
import java.util.ArrayList;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author aleksander
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/beans.xml")
@TransactionConfiguration(transactionManager = "txManager", defaultRollback = true)
@Transactional
public class CoalManagerTest {

    @Autowired
    CoalManager coalManager;

    private final static String NAME1 = "torf";
    private final static String TYPE1 = "kopalny";
    private final static int PERCENT1 = 60;
    private final static String NAME2 = "antracyt";
    private final static String TYPE2 = "pierwiastek";
    private final static int PERCENT2 = 100;
    private final static String NAME3 = "brunatny";
    private final static String TYPE3 = "kopalny";
    private final static int PERCENT3 = 85;
    
    private final static String NAME4 = "kamienny";
    private final static int PERCENT4 = 90;
    
    private Coal newCoal1;
    private Coal newCoal2;
    private Coal newCoal3;
    private List<Coal> coals;

    @Before
    public void initCoals() {
        newCoal1 = new Coal();
        newCoal1.setName(NAME1);
        newCoal1.setType(TYPE1);
        newCoal1.setPercentOfCarbon(PERCENT1);

        newCoal2 = new Coal();
        newCoal2.setName(NAME2);
        newCoal2.setType(TYPE2);
        newCoal2.setPercentOfCarbon(PERCENT2);

        newCoal3 = new Coal();
        newCoal3.setName(NAME3);
        newCoal3.setType(TYPE3);
        newCoal3.setPercentOfCarbon(PERCENT3);

        coalManager.addCoal(newCoal1);
        coalManager.addCoal(newCoal2);
        coalManager.addCoal(newCoal3);

        coals = new ArrayList<>();
        coals = coalManager.getAllCoal();
    }

    @Test
    public void addCoalCheck() {
        Coal coal1 = coals.get(0);
        Assert.assertEquals(newCoal1.getName(), coal1.getName());
        Assert.assertEquals(newCoal1.getType(), coal1.getType());
        Assert.assertEquals(newCoal1.getPercentOfCarbon(), coal1.getPercentOfCarbon());
    }

    @Test
    public void deleteCoalCheck() {
        Coal coalToDelete = coals.get(2);
        coalManager.deleteCoal(coalToDelete);
        List<Coal> coalsAfter = new ArrayList<>();
        coals.remove(coalToDelete);
        coalsAfter = coalManager.getAllCoal();
        Assert.assertNull(coalManager.findCoalById(coalToDelete.getId()));
        Assert.assertTrue(checkTwoCoalLists(coals, coalsAfter));

    }

    @Test
    public void updateCoalCheck() {
        List<Coal> coalsBefore = new ArrayList<>();
        coalsBefore = coalManager.getAllCoal();
        coalsBefore.get(2).setName(NAME4);
        coalsBefore.get(2).setPercentOfCarbon(PERCENT4);
        
        coalManager.updateCoal(coalsBefore.get(2));
        
        List<Coal> coalsAfter = new ArrayList<>();
        coalsAfter = coalManager.getAllCoal();
        Assert.assertTrue(checkTwoCoalLists(coalsAfter, coalsBefore));
    }

    @Test
    public void findCoalByIdCheck() {

        Coal coal2 = coals.get(1);

        Coal coalFoundById = coalManager.findCoalById(coal2.getId());

        Assert.assertEquals(coal2.getName(), coalFoundById.getName());
        Assert.assertEquals(coal2.getType(), coalFoundById.getType());
        Assert.assertEquals(coal2.getPercentOfCarbon(), coalFoundById.getPercentOfCarbon());

    }
    
    private boolean checkTwoCoals(Coal coal1, Coal coal2) {
        return coal1.getName().equals(coal2.getName()) && coal1.getType().equals(coal2.getType()) && coal1.getPercentOfCarbon() == coal2.getPercentOfCarbon() && coal1.getId() == coal2.getId();
    }

    private boolean checkTwoCoalLists(List<Coal> coals1, List<Coal> coals2) {
        if (coals1.size() != coals2.size()) {
            return false;
        } else {
            boolean result = false;
            for (Coal c1 : coals1) {
                result = false;
                for (Coal c2 : coals2) {
                    if (checkTwoCoals(c1, c2)) {
                        result = true;
                        break;
                    }
                }
            }
            return result;
        }
    }
}
