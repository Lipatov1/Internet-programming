package lipatov.lab.familyBudget.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import lipatov.lab.familyBudget.model.FamilyMember;

public interface FamilyMemberRepository extends JpaRepository<FamilyMember, Long> {
}