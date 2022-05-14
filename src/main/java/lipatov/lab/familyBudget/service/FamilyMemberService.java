package lipatov.lab.familyBudget.service;

import lipatov.lab.familyBudget.repository.FamilyMemberRepository;
import org.springframework.transaction.annotation.Transactional;
import lipatov.lab.familyBudget.model.FamilyMember;
import lipatov.lab.util.validation.ValidatorUtil;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.List;
import java.sql.Date;

@Service
public class FamilyMemberService {
    private final FamilyMemberRepository familyMemberRepository;
    private final ValidatorUtil validatorUtil;

    public FamilyMemberService(FamilyMemberRepository familyMemberRepository, ValidatorUtil validatorUtil) {
        this.familyMemberRepository = familyMemberRepository;
        this.validatorUtil = validatorUtil;
    }

    @Transactional
    public FamilyMember addFamilyMember(String firstName, String lastName, String patronymic, String gender, Date birthday) {
        final FamilyMember familyMember = new FamilyMember(firstName, lastName, patronymic, gender, birthday);
        validatorUtil.validate(familyMember);
        return familyMemberRepository.save(familyMember);
    }

    @Transactional(readOnly = true)
    public FamilyMember findFamilyMember(Long id) {
        final Optional<FamilyMember> familyMember = familyMemberRepository.findById(id);
        return familyMember.orElseThrow(() -> new FamilyMemberNotFoundException(id));
    }

    @Transactional(readOnly = true)
    public List<FamilyMember> findAllFamilyMembers() {
        return familyMemberRepository.findAll();
    }

    @Transactional
    public FamilyMember updateFamilyMember(Long id, String firstName, String lastName, String patronymic, String gender, Date birthday) {
        final FamilyMember currentFamilyMember = findFamilyMember(id);
        currentFamilyMember.setFirstName(firstName);
        currentFamilyMember.setLastName(lastName);
        currentFamilyMember.setPatronymic(patronymic);
        currentFamilyMember.setGender(gender);
        currentFamilyMember.setBirthday(birthday);
        validatorUtil.validate(currentFamilyMember);
        return familyMemberRepository.save(currentFamilyMember);
    }

    @Transactional
    public FamilyMember deleteFamilyMember(Long id) {
        final FamilyMember familyMember = findFamilyMember(id);
        familyMemberRepository.delete(familyMember);
        return familyMember;
    }

    @Transactional
    public void deleteAllFamilyMembers() {
        familyMemberRepository.deleteAll();
    }
}