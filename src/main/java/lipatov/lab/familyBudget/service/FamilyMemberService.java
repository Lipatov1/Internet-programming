package lipatov.lab.familyBudget.service;

import org.springframework.transaction.annotation.Transactional;
import lipatov.lab.familyBudget.model.FamilyMember;
import javax.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;
import javax.persistence.PersistenceContext;
import org.springframework.util.StringUtils;
import javax.persistence.EntityManager;
import java.util.List;
import java.sql.Date;

@Service
public class FamilyMemberService {
    @PersistenceContext
    private EntityManager em;

    @Transactional
    public FamilyMember addFamilyMember(String firstName, String lastName, String patronymic, String gender, Date birthday) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName) || !StringUtils.hasText(patronymic) || !StringUtils.hasText(gender) || birthday == null) {
            throw new IllegalArgumentException("One or more parameters are entered incorrectly");
        }
        final FamilyMember familyMember = new FamilyMember(firstName, lastName, patronymic, gender, birthday);
        em.persist(familyMember);
        return familyMember;
    }

    @Transactional(readOnly = true)
    public FamilyMember findFamilyMember(Long id) {
        final FamilyMember familyMember = em.find(FamilyMember.class, id);
        if (familyMember == null) {
            throw new EntityNotFoundException(String.format("Family member with id [%s] is not found", id));
        }
        return familyMember;
    }

    @Transactional(readOnly = true)
    public List<FamilyMember> findAllFamilyMembers() {
        return em.createQuery("select s from FamilyMember s", FamilyMember.class).getResultList();
    }

    @Transactional
    public FamilyMember updateFamilyMember(Long id, String firstName, String lastName, String patronymic, String gender, Date birthday) {
        if (!StringUtils.hasText(firstName) || !StringUtils.hasText(lastName) || !StringUtils.hasText(patronymic) || !StringUtils.hasText(gender) || birthday == null) {
            throw new IllegalArgumentException("One or more parameters are entered incorrectly");
        }
        final FamilyMember currentFamilyMember = findFamilyMember(id);
        currentFamilyMember.setFirstName(firstName);
        currentFamilyMember.setLastName(lastName);
        currentFamilyMember.setPatronymic(patronymic);
        currentFamilyMember.setGender(gender);
        currentFamilyMember.setBirthday(birthday);
        return em.merge(currentFamilyMember);
    }

    @Transactional
    public FamilyMember deleteFamilyMember(Long id) {
        final FamilyMember familyMember = findFamilyMember(id);
        em.remove(familyMember);
        return familyMember;
    }

    @Transactional
    public void deleteAllFamilyMembers() {
        em.createQuery("delete from FamilyMember").executeUpdate();
    }
}