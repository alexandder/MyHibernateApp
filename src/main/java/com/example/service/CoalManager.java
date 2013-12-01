/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.example.service;

import com.example.domain.Coal;
import java.util.List;

/**
 *
 * @author aleksander
 */
public interface CoalManager {
    
    public void addCoal(Coal coal);
    public List<Coal> getAllCoal();
    public void deleteCoal(Coal coal);
    public Coal findCoalById(long id);
    public void updateCoal(Coal coal);
}
