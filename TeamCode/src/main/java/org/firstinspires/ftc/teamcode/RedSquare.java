package org.firstinspires.ftc.teamcode;
import com.qualcomm.hardware.bosch.BNO055IMU;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.vuforia.Vuforia;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.robotcore.external.navigation.AxesOrder;
import org.firstinspires.ftc.robotcore.external.navigation.AxesReference;
import org.firstinspires.ftc.robotcore.external.navigation.Orientation;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;
import java.util.Random;

@Autonomous(name="RedSquare")
public class RedSquare extends encoderDrive {
    //Robot robot = new Robot();

    //Creating an instance of the class doesn't work because it doesn't actually use vuforia
    //ExternalCameraDetection ECD = new ExternalCameraDetection();


//    BNO055IMU imu;
//    Orientation lastAngles = new Orientation();
//    double                  globalAngle;

    // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
    // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
    // and named "imu".



    //skystonePosition is for the movement part of the autonomous
    int skystonePosition = 0;
    //BasicOpMode_Linear basic = new BasicOpMode_Linear();

    private static final String TFOD_MODEL_ASSET = "Skystone.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Stone";
    private static final String LABEL_SECOND_ELEMENT = "Skystone";

    public String[] position = new String[3];
    //skystonePlace is for the runVuforia() method to return
    int skystonePlace;

    private static final String VUFORIA_KEY =
            "AWgpcWb/////AAABmaGz2/7eLkNnmdf7SE2FZHVj9lqZbwrKkg/8If3+fcqfKrCTI8mB7LVi91U36rl9kbu" +
                    "9YQN4E885gcuB1EuzBZM+aDWlSel0plj5FnPPsz4+S4Z9jfMgPyCtF7yOJ93ijYIT/LWznGg34Q" +
                    "ljTOfBIsABnXFd/2iZKuTD2XMewqk+Fw+vJLEZK4SR18I4Cs/L4HXUK9BO/3KwQPTQc5nRisUJVV" +
                    "sxv4wcNFGaECpBrUdg5icwVdMfuPbPjDwtIpX7wuDLdLFXZkveohubFUlcnPGFGrj7QFald5V92+" +
                    "1C8fmKfcmv/LfuHVWJQ/4bgSkE1NcN+G/bmlnV1Wv8clJ3JzV/w3FK2+1GX6vXsD44rtHt";

    /**
     * {@link #vuforia} is the variable we will use to store our instance of the Vuforia
     * localization engine.
     */
    private VuforiaLocalizer vuforia;

    /**
     * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
     * Detection engine.
     */
    private TFObjectDetector tfod;

    @Override
    public void runOpMode() {
        super.runOpMode();


        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
        // and named "imu".

        //}

        sleep(3000);


//        BNO055IMU.Parameters parameters = new BNO055IMU.Parameters();
//
//        parameters.mode                = BNO055IMU.SensorMode.IMU;
//        parameters.angleUnit           = BNO055IMU.AngleUnit.DEGREES;
//        parameters.accelUnit           = BNO055IMU.AccelUnit.METERS_PERSEC_PERSEC;
//        parameters.loggingEnabled      = false;
//
//        // Retrieve and initialize the IMU. We expect the IMU to be attached to an I2C port
//        // on a Core Device Interface Module, configured to be a sensor of type "AdaFruit IMU",
//        // and named "imu".
//        imu = hardwareMap.get(BNO055IMU.class, "imu");
//
//        imu.initialize(parameters);
//
//        telemetry.addData("Mode", "calibrating...");
//        telemetry.update();
//
//        // make sure the imu gyro is calibrated before continuing.
//        while (!isStopRequested() && !imu.isGyroCalibrated())
//        {
//            sleep(50);
//            idle();
//        }


        //resetAngle();

        moveChicken(-1);
        moveFoundation(1);

        telemetry.addData("Mode", "waiting for start");
        //telemetry.addData("imu calib status", imu.getCalibrationStatus().toString());
        telemetry.update();
//
//        // wait for start button.
//
        waitForStart();

//        encoderStrafe(speed, 1, 830);
//        skystonePosition = runVuforia();
//        encoderStrafe(speed, 1, 575);

        //MOVE TO ALIGN WITH SKYSTONE POSITION


        //telemetry.addData("before turn", getAngle());
        //toAngle();

//        if (skystonePosition == 1) {
//            //encoderBack(0.2, 350);
//            encoderBack(0.5, 300);
//            encoderStrafe(0.4, 1, 100);
//            //encoderTurn(0.3, 1, 60, 60);
//            moveChicken(1);
//            encoderStrafe(speed, -1, 450);
//            encoderForward(0.5, 2300);
//            moveChicken(-1);
//            //encoderTurn(0.3, 1, 40, 40);
//            //encoderStrafe(0.5, -1, 50);
//
//            encoderBack(0.5, 3600);
//            encoderForward(0.5, 50);
//            encoderStrafe(speed, 1, 600);
//            moveChicken(1);
//            encoderStrafe(speed, -1, 770);
//            encoderBack(speed, 200);
//            encoderForward(0.5, 3600);
//            moveChicken(-1);
//            encoderBack(0.5, 550);
//
//
//           encoderForward(0.6, 1650);
//            encoderTurn(0.5, -1, 730, 730);
//            encoderStrafe(0.5, -1, 500);
//            encoderStrafe(0.5, 1, 650);
//            encoderForward(0.5, 450);
//            moveFoundation(-1);
//            sleep(1000);
//            encoderBack(0.7, 2000);
//            moveFoundation(1);
//            encoderStrafe(0.5, 1, 2150);
//        }
//        if (skystonePosition == 2 || skystonePosition == 0) {
//            //move a bit less to the right than position 1
//            encoderBack(0.2, 60);
//            encoderStrafe(0.5, 1, 125);
//            //encoderTurn(0.3, 1, 60, 60);
//            moveChicken(1);
//            encoderStrafe(speed, -1, 520);
//            encoderForward(0.5, 1900);
//            moveChicken(-1);
//            encoderStrafe(speed, -1, 50);
//            //encoderTurn(0.3, 1, 50, 50);
//
//            encoderBack(0.5, 3300);
//            encoderForward(0.3, 344);
//            encoderStrafe(speed, 1, 460);
//            moveChicken(1);
//            encoderStrafe(speed, -1, 650);
//            encoderForward(0.5, 3100);
//            moveChicken(-1);
//            encoderBack(0.5, 600);
//
//
//            encoderForward(0.5, 2300);
//            encoderTurn(0.5, -1, 730, 730);
//            encoderStrafe(0.5, -1, 500);
//            encoderStrafe(0.5, 1, 650);
//            encoderForward(0.5, 450);
//            moveFoundation(-1);
//            sleep(1000);
//            encoderBack(0.5, 2000);
//            moveFoundation(1);
//            encoderStrafe(0.5, 1, 2150);
//        }
//        if (skystonePosition == 3) {
//            //move the same amount to the left as position 2
//            encoderForward(0.2, 390);
//            //encoderTurn(0.3, 1, 70, 70);
//            moveChicken(1);
//            encoderStrafe(speed, -1, 450);
//            encoderForward(0.5, 2000);
//            moveChicken(-1);
//            encoderStrafe(speed, -1, 50);
//            //encoderTurn(0.3, 1, 30, 30);
//
//            encoderBack(0.8, 3800);
//            encoderForward(.5,600);
//            encoderStrafe(speed, 1, 680);
//            moveChicken(1);
//            encoderStrafe(speed, -1, 620);
//            encoderForward(0.5, 3000);
//            moveChicken(-1);
//
//
//            encoderForward(0.5, 2300);
//            //encoderTurn(0.5, -1, 730, 730);
//            encoderStrafe(0.5, -1, 500);
//            encoderStrafe(0.5, 1, 650);
//            encoderForward(0.5, 450);
//            moveFoundation(-1);
//            sleep(1000);
//            encoderBack(0.5, 2000);
//            moveFoundation(1);
//            encoderStrafe(0.5, 1, 2150);
//        }


    }


    public int runVuforia() {
        initVuforia();

        if (ClassFactory.getInstance().canCreateTFObjectDetector()) {
            initTfod();
        } else {
            telemetry.addData("Sorry!", "This device is not compatible with TFOD");
        }

        if (tfod != null) {
            tfod.activate();
        }

//        telemetry.addData(">", "Press Play to start op mode");
//        telemetry.update();
        //waitForStart();

        if (opModeIsActive()) {
            boolean isDoneWithVuforia = false;
            int isdone = 0;
            while (opModeIsActive()
                    && !isDoneWithVuforia
            ) {
                if (tfod != null) {
                    // getUpdatedRecognitions() will return null if no new information is available since
                    // the last time that call was made.
                    List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
                    if (updatedRecognitions != null /*|| getRuntime() > 7*/) {
                        telemetry.addData("# Object Detected", updatedRecognitions.size());
                        // step through the list of recognitions and display boundary info.
                        int i = 0;
                        for (Recognition recognition : updatedRecognitions) {
                            //if (getRuntime() > 7)
                            {
                                telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                                telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                        recognition.getLeft(), recognition.getTop());
                                telemetry.addData(String.format("  right,bottom (%d)", i), "%.03f , %.03f",
                                        recognition.getRight(), recognition.getBottom());
                                //MAYBE
                                //PUT IN i++ HERE TO CHANGE POSITION OF ELEMENT?? - A
                                i++;

                                if (recognition.getLeft() < 150 && recognition.getRight() < 600) {
                                    position[2] = recognition.getLabel();
                                }
                                if (recognition.getLeft() > 240 && recognition.getRight() > 630) {
                                    position[1] = recognition.getLabel();
                                }
                                //isdone++;
                                //if (isdone==2) {
                                    isDoneWithVuforia = true;
                                //}
                            }
                        }
//
//                        if (position[1] == "Stone" && position[2] == "Stone") {
//                            position[0] = "Skystone";
//                        }
//                        if (position[1] != "Stone" || position[2] != "Stone"){
//                            position[0] = "Stone";
//                        }

                        //telemetry.addData("block 1 is --", position[0]);
                        telemetry.addData("block 2 is --", position[1]);
                        telemetry.addData("block 3 is --", position[2]);

                        telemetry.update();

//                        if (position[0] == "Skystone") ;
//                        {
//                            skystonePlace = 1;
//                        }
                        if (position[1] == "Skystone") {
                            skystonePlace = 2;
                        }
                        if (position[2] == "Skystone") {
                            skystonePlace = 3;
                        }
                        if (position[1] != "Skystone" && position[2] != "Skystone") {
                            skystonePlace = 1;
                        }
                    }
                }
            }
        }

        if (tfod != null) {
            tfod.shutdown();
        }
        telemetry.addData("I made it!", "skystonePlace");
        telemetry.update();
        return skystonePlace;
    }

    public int getSkystone() {
        return skystonePlace;
    }

    private void initVuforia() {

        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam 1");

        //  Instantiate the Vuforia engine
        //this is apparently what I was having a problem with. By making a object of the ECD class
        //I wasn't getting the vuforia engine instantiated
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minimumConfidence = 0.8;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }

}



