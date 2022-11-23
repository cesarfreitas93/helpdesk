package pe.farell.go.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.farell.go.backend.model.entity.TicketEntity;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<TicketEntity, Integer> {
    List<TicketEntity> findAllByProject(Integer idProject);

    List<TicketEntity> findAllByProjectAndSprint(Integer idProject, Integer idSprint);

    List<TicketEntity> findAllByProjectAndSprintAndAndAssignedTo(Integer idProject, Integer idSprint, Integer idPerson);
}
