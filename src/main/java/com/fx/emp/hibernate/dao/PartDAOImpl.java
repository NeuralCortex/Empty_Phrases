package com.fx.emp.hibernate.dao;

import com.fx.emp.Globals;
import com.fx.emp.hibernate.HibernateUtil;
import com.fx.emp.hibernate.entity.Part;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author pscha
 */
public class PartDAOImpl implements PartDAO {

    private List<Part> createPart0List() {
        List<Part> partList = new ArrayList<>();
        partList.add(new Part(0, "synchron"));
        partList.add(new Part(0, "aktiv"));
        partList.add(new Part(0, "konjunktur"));
        partList.add(new Part(0, "organisatorisch"));
        partList.add(new Part(0, "ambivalent"));
        partList.add(new Part(0, "typisch deutsch"));
        partList.add(new Part(0, "amtsintern"));
        partList.add(new Part(0, "wissenschaftlich"));
        partList.add(new Part(0, "bürgerfreundlich"));
        partList.add(new Part(0, "modifiziert"));
        partList.add(new Part(0, "implizit"));
        partList.add(new Part(0, "operativ"));
        return partList;
    }

    private List<Part> createPart1List() {
        List<Part> partList = new ArrayList<>();
        partList.add(new Part(1, "punktuelle"));
        partList.add(new Part(1, "orientierte"));
        partList.add(new Part(1, "qualifizierte"));
        partList.add(new Part(1, "progressive"));
        partList.add(new Part(1, "bedingte"));
        partList.add(new Part(1, "maximierte"));
        partList.add(new Part(1, "konzentrierte"));
        partList.add(new Part(1, "integrierte"));
        partList.add(new Part(1, "flexible"));
        partList.add(new Part(1, "strukturierte"));
        partList.add(new Part(1, "alternative"));
        return partList;
    }

    private List<Part> createPart2List() {
        List<Part> partList = new ArrayList<>();
        partList.add(new Part(2, "Steuerrechts"));
        partList.add(new Part(2, "Reform"));
        partList.add(new Part(2, "Gewerkschafts"));
        partList.add(new Part(2, "Veranlagungs"));
        partList.add(new Part(2, "Aufgabenverteilungs"));
        partList.add(new Part(2, "Personal"));
        partList.add(new Part(2, "Behörden"));
        partList.add(new Part(2, "Vorgesetzten"));
        partList.add(new Part(2, "Kollegialitäts"));
        partList.add(new Part(2, "Arbeitsplatz"));
        partList.add(new Part(2, "Fakten"));
        return partList;
    }

    private List<Part> createPart3List() {
        List<Part> partList = new ArrayList<>();
        partList.add(new Part(3, "problematik"));
        partList.add(new Part(3, "effizienz"));
        partList.add(new Part(3, "praktiken"));
        partList.add(new Part(3, "programmierung"));
        partList.add(new Part(3, "tendenz"));
        partList.add(new Part(3, "konzepte"));
        partList.add(new Part(3, "prinzipien"));
        partList.add(new Part(3, "komponenten"));
        partList.add(new Part(3, "schwierigkeiten"));
        return partList;
    }

    @Override
    public Part addPart(Part part) {
        try {
            Session session = HibernateUtil.beginTransaction();
            session.save(part);
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            try {
                HibernateUtil.rollbackTransaction();
            } catch (Exception ex_rollback) {
                //_log.error(ex_rollback.getMessage());
            }
        }
        return part;
    }

    @Override
    public void createDB() {
        try {
            Session session = HibernateUtil.beginTransaction();
            for (Part part : createPart0List()) {
                session.save(part);
            }
            for (Part part : createPart1List()) {
                session.save(part);
            }
            for (Part part : createPart2List()) {
                session.save(part);
            }
            for (Part part : createPart3List()) {
                session.save(part);
            }
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            try {
                HibernateUtil.rollbackTransaction();
            } catch (Exception ex_rollback) {
                //_log.error(ex_rollback.getMessage());
            }
        }
    }

    @Override
    public List<Part> getPartList(Globals.PART part) {
        Session session = HibernateUtil.beginTransaction();
        List<Part> list = session.createNativeQuery("select * from part p where p.part=:pid order by id")
                .setParameter("pid", part.ordinal())
                .addScalar("id", StandardBasicTypes.LONG)
                .addScalar("part", StandardBasicTypes.LONG)
                .addScalar("phrase", StandardBasicTypes.STRING)
                .setResultTransformer(Transformers.aliasToBean(Part.class))
                .list();
        HibernateUtil.commitTransaction();
        return list;
    }

    @Override
    public void deleteAll() {
        Session session = HibernateUtil.beginTransaction();
        session.createNativeQuery("delete from part").executeUpdate();
        HibernateUtil.commitTransaction();
    }

    @Override
    public void delPart(Part part) {
        try {
            Session session = HibernateUtil.beginTransaction();
            session.delete(part);
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            try {
                HibernateUtil.rollbackTransaction();
            } catch (Exception ex_rollback) {
                //_log.error(ex_rollback.getMessage());
            }
        }
    }

    @Override
    public Part renamePart(Part part) {
        try {
            Session session = HibernateUtil.beginTransaction();
            session.update(part);
            HibernateUtil.commitTransaction();
        } catch (Exception ex) {
            try {
                HibernateUtil.rollbackTransaction();
            } catch (Exception ex_rollback) {
                //_log.error(ex_rollback.getMessage());
            }
        }
        return part;
    }
}
