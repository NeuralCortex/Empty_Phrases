package com.fx.emp.hibernate.dao;

import com.fx.emp.Globals;
import com.fx.emp.hibernate.entity.Part;
import java.util.List;

/**
 *
 * @author pscha
 */
public interface PartDAO {

    public Part addPart(Part part);
    
    public void delPart(Part part);
    
    public Part renamePart(Part part);
    
    public void deleteAll();
    
    public List<Part> getPartList(Globals.PART part);

    public void createDB();
}
