package com.avadhuta.testmaven.web;

import com.avadhuta.testmaven.dao.*;
import com.avadhuta.testmaven.model.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.time.*;
import java.util.*;

import static javax.transaction.Transactional.TxType.*;

/**
 *
 */
@Service
public class StubHelper {

    private final MineJpaRepository mineJpaRepository;
    private final PersonJpaRepository personJpaRepository;
    private final TeamLeaderJpaRepository teamLeaderJpaRepository;
    private final CrystalTypeJpaRepository crystalTypeJpaRepository;
    private final ShiftJpaRepository shiftJpaRepository;
    private final SorterJpaRepository sorterJpaRepository;
    private final AppraiserJpaRepository appraiserJpaRepository;
    private final ContainerCellTypeJpaRepository containerCellTypeJpaRepository;
    private final ContainerTypeJpaRepository containerTypeJpaRepository;
    private final ContainerJpaRepository containerJpaRepository;

    public StubHelper(MineJpaRepository mineJpaRepository, PersonJpaRepository personJpaRepository,
            TeamLeaderJpaRepository teamLeaderJpaRepository, CrystalTypeJpaRepository crystalTypeJpaRepository,
            ShiftJpaRepository shiftJpaRepository, SorterJpaRepository sorterJpaRepository,
            AppraiserJpaRepository appraiserJpaRepository,
            ContainerCellTypeJpaRepository containerCellTypeJpaRepository,
            ContainerTypeJpaRepository containerTypeJpaRepository, ContainerJpaRepository containerJpaRepository) {
        this.mineJpaRepository = mineJpaRepository;
        this.personJpaRepository = personJpaRepository;
        this.teamLeaderJpaRepository = teamLeaderJpaRepository;
        this.crystalTypeJpaRepository = crystalTypeJpaRepository;
        this.shiftJpaRepository = shiftJpaRepository;
        this.sorterJpaRepository = sorterJpaRepository;
        this.appraiserJpaRepository = appraiserJpaRepository;
        this.containerCellTypeJpaRepository = containerCellTypeJpaRepository;
        this.containerTypeJpaRepository = containerTypeJpaRepository;
        this.containerJpaRepository = containerJpaRepository;
    }

    @Transactional (REQUIRES_NEW)
    public DataForShiftCreation createDataForShiftCreation() {
        long time = System.currentTimeMillis();
        Mine mine = createMine(time);
        Person person = createPerson(time);
        TeamLeader teamLeader = createTeamLeader(time, person);
        CrystalType crystalType1 = createCrystalType(time, 1);
        CrystalType crystalType2 = createCrystalType(time, 2);

        return new DataForShiftCreation(time, mine, person, teamLeader, crystalType1, crystalType2);
    }

    @Transactional (REQUIRES_NEW)
    public Sorter createSorter() {
        long time = System.currentTimeMillis();
        Person person = createPerson(time);
        Sorter sorter = createSorter(time, person);
        return sorter;
    }

    @Transactional (REQUIRES_NEW)
    public Appraiser createAppraiser() {
        long time = System.currentTimeMillis();
        Person person = createPerson(time);
        Appraiser appraiser = createAppraiser(time, person);
        return appraiser;
    }

    @Transactional (REQUIRES_NEW)
    public ContainerCellType createContainerCellType(ContainerCellClass cellClass,
            double widthX, double widthY, double height) {
        ContainerCellType cellType = new ContainerCellType();
        cellType.setName("Ячейка " + cellClass + " " + widthX + "x" + widthY + "x" + height);
        cellType.setCellClass(cellClass);
        cellType.setWidthX(widthX);
        cellType.setWidthY(widthY);
        cellType.setHeight(height);
        cellType.setVolume(widthX * widthY * height);
        return this.containerCellTypeJpaRepository.save(cellType);
    }

    @Transactional (REQUIRES_NEW)
    public ContainerType createContainerType(int index, ContainerCellType personalCellType, int personalQuantity,
            ContainerCellType protectedCellType, int protectedQuantity,
            ContainerCellType heapCellType, int heapQuantity) {
        ContainerType containerType = new ContainerType();
        containerType.setName("ContainerType #" + index);
        containerType.setContainerMass(147.0 * index);

        HashSet<ContainerCellDescription> details = new HashSet<>();
        if (personalCellType != null) {
            ContainerCellDescription cellDescription = createContainerCellDescription(
                    containerType, personalCellType, personalQuantity);
            details.add(cellDescription);
        }
        if (protectedCellType != null) {
            ContainerCellDescription cellDescription = createContainerCellDescription(
                    containerType, protectedCellType, protectedQuantity);
            details.add(cellDescription);
        }
        if (heapCellType != null) {
            ContainerCellDescription cellDescription = createContainerCellDescription(
                    containerType, heapCellType, heapQuantity);
            details.add(cellDescription);
        }

        containerType.setDetails(details);
        return this.containerTypeJpaRepository.save(containerType);
    }

    @Transactional (REQUIRES_NEW)
    public Container createContainer(ContainerType containerType) {
        Container container = new Container();
        container.setContainerBarCode(String.valueOf(System.currentTimeMillis()));
        container.setContainerState(ContainerState.EMPTY);
        container.setContainerType(containerType);
        return this.containerJpaRepository.save(container);
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private ContainerCellDescription createContainerCellDescription(ContainerType containerType,
            ContainerCellType cellType, int quantity) {
        ContainerCellDescription cellDescription = new ContainerCellDescription();
        cellDescription.setParent(containerType);
        cellDescription.setCellType(cellType);
        cellDescription.setQuantity(quantity);
        return cellDescription;
    }

    private Appraiser createAppraiser(long time, Person person) {
        Appraiser appraiser = new Appraiser();
        appraiser.setPerson(person);
        appraiser.setNumberOfWorkingPlace("WorkingPlace #" + time);
        return this.appraiserJpaRepository.save(appraiser);
    }

    private Mine createMine(long time) {
        Mine mine = new Mine();
        mine.setName("Mine #" + time);
        return this.mineJpaRepository.save(mine);
    }

    private Person createPerson(long time) {
        Person person = new Person();
        person.setDateOfBirth(LocalDate.now());
        person.setFirstName("First Name #" + time);
        person.setMiddleName("Middle Name #" + time);
        person.setLastName("Last Name #" + time);
        return this.personJpaRepository.save(person);
    }

    private TeamLeader createTeamLeader(long time, Person person) {
        TeamLeader teamLeader = new TeamLeader();
        teamLeader.setPerson(person);
        teamLeader.setTeamName("Team #" + time);
        return this.teamLeaderJpaRepository.save(teamLeader);
    }

    private Sorter createSorter(long time, Person person) {
        Sorter sorter = new Sorter();
        sorter.setPerson(person);
        sorter.setNumberOfWorkingPlace("WorkingPlace #" + time);
        return this.sorterJpaRepository.save(sorter);
    }

    private CrystalType createCrystalType(long time, int index) {
        CrystalType crystalType = new CrystalType();
        crystalType.setName("Name #" + time + " - " + index);
        crystalType.setHeapDencity(10.0 + index);
        crystalType.setHardness(index);
        return this.crystalTypeJpaRepository.save(crystalType);
    }

    public static class DataForShiftCreation {
        public final long time;
        public final Mine mine;
        public final Person person;
        public final TeamLeader teamLeader;
        public final CrystalType crystalType1;
        public final CrystalType crystalType2;

        public DataForShiftCreation(long time, Mine mine, Person person, TeamLeader teamLeader,
                CrystalType crystalType1,
                CrystalType crystalType2) {
            this.time = time;
            this.mine = mine;
            this.person = person;
            this.teamLeader = teamLeader;
            this.crystalType1 = crystalType1;
            this.crystalType2 = crystalType2;
        }
    }

}