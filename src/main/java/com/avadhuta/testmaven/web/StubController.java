package com.avadhuta.testmaven.web;

import com.avadhuta.testmaven.dao.*;
import com.avadhuta.testmaven.model.*;
import com.avadhuta.testmaven.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.*;
import java.time.*;
import java.util.*;

import static javax.transaction.Transactional.TxType.*;

/**
 * http://localhost:8080/createShift
 * http://localhost:8080/displayShift?id=6
 * http://localhost:8080/createClassificationReport?id=7
 * http://localhost:8080/displayClassificationReport?id=11
 * http://localhost:8080/createAppraiseReport?id=13
 * http://localhost:8080/displayAppraiseReport?id=16
 *
 * http://localhost:8080/createContainers
 */
@RestController
public class StubController {

    private final MiningService miningService;
    private final StubHelper stubHelper;
    private final ChainedUpdatesService chainedUpdatesService;
    private final LogisticService logisticService;
    private final ShiftJpaRepository shiftJpaRepository;
    private final ShiftOutputJpaRepository shiftOutputJpaRepository;
    private final CrystalTypeJpaRepository crystalTypeJpaRepository;
    private final ClassificationReportJpaRepository classificationReportJpaRepository;
    private final ClassificationDetailsJpaRepository classificationDetailsJpaRepository;
    private final AppraiseReportJpaRepository appraiseReportJpaRepository;

    public StubController(@Qualifier ("MiningService") MiningService miningService, StubHelper stubHelper,
            ChainedUpdatesService chainedUpdatesService, LogisticService logisticService,
            ShiftJpaRepository shiftJpaRepository,
            ShiftOutputJpaRepository shiftOutputJpaRepository, CrystalTypeJpaRepository crystalTypeJpaRepository,
            ClassificationReportJpaRepository classificationReportJpaRepository,
            ClassificationDetailsJpaRepository classificationDetailsJpaRepository,
            AppraiseReportJpaRepository appraiseReportJpaRepository) {
        this.miningService = miningService;
        this.stubHelper = stubHelper;
        this.chainedUpdatesService = chainedUpdatesService;
        this.logisticService = logisticService;
        this.shiftJpaRepository = shiftJpaRepository;
        this.shiftOutputJpaRepository = shiftOutputJpaRepository;
        this.crystalTypeJpaRepository = crystalTypeJpaRepository;
        this.classificationReportJpaRepository = classificationReportJpaRepository;
        this.classificationDetailsJpaRepository = classificationDetailsJpaRepository;
        this.appraiseReportJpaRepository = appraiseReportJpaRepository;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping ("/createShift")
    @Transactional (NEVER)
    public String createShift() {
        StubHelper.DataForShiftCreation dataForShiftCreation = this.stubHelper.createDataForShiftCreation();

        Shift shift = createShift(dataForShiftCreation);
        shift = miningService.saveShift(shift);
        this.chainedUpdatesService.calculateAllQueue();

        return "Shift: " + shift;
    }

    @RequestMapping ("/updateShift")
    @Transactional (NEVER)
    public String updateShift(@RequestParam (value = "id") Long id,
            @RequestParam (value = "value", defaultValue = "7890.0") Double value) {
        Shift shift = this.shiftJpaRepository.findOne(id);

        int i = 1;
        for (ShiftOutput shiftOutput : shift.getDetails()) {
            shiftOutput.setShiftOutput(value + i);
            i++;
        }
        shift = this.miningService.saveShift(shift);
        this.chainedUpdatesService.calculateAllQueue();

        return "Shift: " + shift;
    }

    @RequestMapping ("/displayShift")
    @Transactional (NEVER)
    public String displayShift(@RequestParam (value = "id") Long id) {
        Shift shift = this.shiftJpaRepository.findOne(id);
        return "Shift: " + shift;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping ("/createClassificationReport")
    @Transactional (NEVER)
    public String createClassificationReport(@RequestParam (value = "id") Long id) {
        ShiftOutput shiftOutput = this.shiftOutputJpaRepository.findOne(id);
        Sorter sorter = this.stubHelper.createSorter();
        ClassificationReport classificationReport = createClassificationReport(shiftOutput, sorter);

        classificationReport = this.miningService.saveClassificationReport(classificationReport);
        this.chainedUpdatesService.calculateAllQueue();

        return "ClassificationReport: " + classificationReport;
    }

    @RequestMapping ("/displayClassificationReport")
    @Transactional (NEVER)
    public String displayClassificationReport(@RequestParam (value = "id") Long id) {
        ClassificationReport classificationReport = this.classificationReportJpaRepository.findOne(id);
        return "ClassificationReport: " + classificationReport;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping ("/createAppraiseReport")
    @Transactional (NEVER)
    public String createAppraiseReport(@RequestParam (value = "id") Long id) {
        ClassificationDetails classificationDetails = this.classificationDetailsJpaRepository.findOne(id);
        Appraiser appraiser = this.stubHelper.createAppraiser();
        AppraiseReport appraiseReport = createAppraiseReport(classificationDetails, appraiser);

        appraiseReport = this.miningService.saveAppraiseReport(appraiseReport);
        this.chainedUpdatesService.calculateAllQueue();

        return "AppraiseReport: " + appraiseReport;
    }

    @RequestMapping ("/displayAppraiseReport")
    @Transactional (NEVER)
    public String displayAppraiseReport(@RequestParam (value = "id") Long id) {
        AppraiseReport appraiseReport = this.appraiseReportJpaRepository.findOne(id);
        return "AppraiseReport: " + appraiseReport;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping ("/createContainers")
    @Transactional (NEVER)
    public String createContainers() {
        // Создаём типы клеток контейнеров.
        ContainerCellType cellTypeHEAP_900x300x500 = this.stubHelper.createContainerCellType(
                ContainerCellClass.HEAP, 900, 300, 500);
        ContainerCellType cellTypeHEAP_450x300x250 = this.stubHelper.createContainerCellType(
                ContainerCellClass.HEAP, 450, 300, 250);
        ContainerCellType cellTypePROTECTED_80x80x20 = this.stubHelper.createContainerCellType(
                ContainerCellClass.PROTECTED, 80, 80, 20);
        ContainerCellType cellTypePERSONAL_50x50x50 = this.stubHelper.createContainerCellType(
                ContainerCellClass.PERSONAL, 50, 50, 50);
        ContainerCellType cellTypePERSONAL_20x100x30 = this.stubHelper.createContainerCellType(
                ContainerCellClass.PERSONAL, 20, 100, 30);
        ContainerCellType cellTypePERSONAL_10x10x20 = this.stubHelper.createContainerCellType(
                ContainerCellClass.PERSONAL, 10, 10, 20);

        // Создаём типы контейнеров.
        ContainerType containerType1 = this.stubHelper.createContainerType(1, cellTypePERSONAL_50x50x50, 10,
                cellTypePROTECTED_80x80x20, 4, null, 0);
        ContainerType containerType2 = this.stubHelper.createContainerType(1, null, 0, null, 0,
                cellTypeHEAP_900x300x500, 1);
        ContainerType containerType3 = this.stubHelper.createContainerType(1, cellTypePERSONAL_20x100x30, 2,
                null, 0, cellTypeHEAP_450x300x250, 2);
        ContainerType containerType4 = this.stubHelper.createContainerType(1, cellTypePERSONAL_10x10x20, 40,
                null, 0, null, 0);

        // Создаём экземпляры контейнеров.
        ArrayList<Long> ids = new ArrayList<>();
        ids.add(this.stubHelper.createContainer(containerType1).getId());
        ids.add(this.stubHelper.createContainer(containerType1).getId());
        ids.add(this.stubHelper.createContainer(containerType2).getId());
        ids.add(this.stubHelper.createContainer(containerType2).getId());
        ids.add(this.stubHelper.createContainer(containerType3).getId());
        ids.add(this.stubHelper.createContainer(containerType3).getId());
        ids.add(this.stubHelper.createContainer(containerType3).getId());
        ids.add(this.stubHelper.createContainer(containerType4).getId());
        ids.add(this.stubHelper.createContainer(containerType4).getId());
        ids.add(this.stubHelper.createContainer(containerType4).getId());
        ids.add(this.stubHelper.createContainer(containerType4).getId());
        ids.add(this.stubHelper.createContainer(containerType4).getId());

        return "Identifiers: " + ids;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    @RequestMapping ("/startLoadingProcess")
    @Transactional (NEVER)
    public String startLoadingProcess() {
        LoadingProcess loadingProcess = this.logisticService.createLoadingProcess();
        this.logisticService.startLoadingProcess(loadingProcess.getId());
        return "Started LoadingProcess: " + loadingProcess;
    }

    ///////////////////////////////////////////////////////////////////////////////////////////////////////

    private AppraiseReport createAppraiseReport(ClassificationDetails classificationDetails, Appraiser appraiser) {
        long time = System.currentTimeMillis();
        Random random = new Random();
        AppraiseReport appraiseReport = new AppraiseReport();
        appraiseReport.setDate(LocalDate.now());
        appraiseReport.setAppraiser(appraiser);
        appraiseReport.setClassificationDetails(classificationDetails);
        appraiseReport.setDetails(new HashSet<>(Arrays.asList(
                createCrystalDetails(time, 0, appraiseReport, random),
                createCrystalDetails(time, 1, appraiseReport, random),
                createCrystalDetails(time, 2, appraiseReport, random),
                createCrystalDetails(time, 3, appraiseReport, random),
                createCrystalDetails(time, 4, appraiseReport, random),
                createCrystalDetails(time, 5, appraiseReport, random),
                createCrystalDetails(time, 6, appraiseReport, random),
                createCrystalDetails(time, 7, appraiseReport, random)
        )));
        appraiseReport.setWriteOffMass(15.0);
        return appraiseReport;
    }

    private CrystalDetails createCrystalDetails(long time, int index, AppraiseReport appraiseReport, Random random) {
        CrystalDetails crystalDetails = new CrystalDetails();
        crystalDetails.setAppraiseReport(appraiseReport);
        crystalDetails.setCrystalType(appraiseReport.getClassificationDetails().getCrystalType());
        crystalDetails.setCrystalCostClassification(CrystalCostClassification.values()[index]);
        crystalDetails.setCrystalClearnessType(CrystalClearnessType.values()[index % 4]);
        crystalDetails.setMass(13.0 * index);
        crystalDetails.setWidthX(5.0 + random.nextInt(10));
        crystalDetails.setWidthY(5.0 + random.nextInt(10));
        crystalDetails.setHeight(5.0 + random.nextInt(10));
        crystalDetails.setDescription("Description #" + time);
        crystalDetails.setСrystalBarCode(String.valueOf(time));
        crystalDetails.setDefects(new HashSet<>(Arrays.asList(
                createCrystalDefectDescription(crystalDetails, 0),
                createCrystalDefectDescription(crystalDetails, 2)
        )));
        return crystalDetails;
    }

    private CrystalDefectDescription createCrystalDefectDescription(CrystalDetails crystalDetails, int index) {
        CrystalDefectDescription defectDescription = new CrystalDefectDescription();
        defectDescription.setParent(crystalDetails);
        defectDescription.setDefectType(CrystalDefectType.values()[index % 3]);
        defectDescription.setDescription("Description #" + index);
        return defectDescription;
    }

    private Shift createShift(StubHelper.DataForShiftCreation data) {
        Shift shift = new Shift();
        shift.setMine(data.mine);
        shift.setDate(LocalDate.now());
        shift.setShiftType(ShiftType.SECOND);
        shift.setTeamLeader(data.teamLeader);
        shift.setDetails(new HashSet<>(Arrays.asList(
                createShiftOutput(data.time, 1, shift, data.crystalType1),
                createShiftOutput(data.time, 2, shift, data.crystalType2)
        )));
        shift.setComment("Comment #" + data.time);
        return shift;
    }

    private ShiftOutput createShiftOutput(long time, int index, Shift shift, CrystalType crystalType) {
        ShiftOutput shiftOutput = new ShiftOutput();
        shiftOutput.setParent(shift);
        shiftOutput.setCrystalType(crystalType);
        shiftOutput.setShiftOutput(1230.0 + index);
        shiftOutput.setVesselBarCode(String.valueOf(time));
        return shiftOutput;
    }

    private ClassificationReport createClassificationReport(ShiftOutput shiftOutput, Sorter sorter) {
        List<CrystalType> crystalTypes = this.crystalTypeJpaRepository.findAll();

        long time = System.currentTimeMillis();
        ClassificationReport report = new ClassificationReport();
        report.setDate(LocalDate.now());
        report.setSorter(sorter);
        report.setShiftOutput(shiftOutput);
        report.setDetails(new HashSet<>(Arrays.asList(
                createClassificationDetails(report, crystalTypes, time, 0),
                createClassificationDetails(report, crystalTypes, time, 1)
        )));
        report.setWriteOffMass(456.0);
        report.setComment("Comment #" + time);

        return report;
    }

    private ClassificationDetails createClassificationDetails(ClassificationReport report,
            List<CrystalType> crystalTypes, long time, int index) {
        ClassificationDetails classificationDetails = new ClassificationDetails();
        classificationDetails.setParent(report);
        classificationDetails.setCrystalType(crystalTypes.get(index));
        classificationDetails.setCrystalCostClassification(CrystalCostClassification.values()[index * 7]);
        classificationDetails.setClassifiedOutput(500.0 + index);
        classificationDetails.setVesselBarCode(String.valueOf(time));
        return classificationDetails;
    }

}