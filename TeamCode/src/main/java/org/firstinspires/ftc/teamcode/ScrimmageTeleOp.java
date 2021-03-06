/* Copyright (c) 2017 FIRST. All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without modification,
 * are permitted (subject to the limitations in the disclaimer below) provided that
 * the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this list
 * of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice, this
 * list of conditions and the following disclaimer in the documentation and/or
 * other materials provided with the distribution.
 *
 * Neither the name of FIRST nor the names of its contributors may be used to endorse or
 * promote products derived from this software without specific prior written permission.
 *
 * NO EXPRESS OR IMPLIED LICENSES TO ANY PARTY'S PATENT RIGHTS ARE GRANTED BY THIS
 * LICENSE. THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE
 * FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL
 * DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
 * SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER
 * CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY,
 * OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


/**
 * This file contains an minimal example of a Linear "OpMode". An OpMode is a 'program' that runs in either
 * the autonomous or the teleop period of an FTC match. The names of OpModes appear on the menu
 * of the FTC Driver Station. When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all linear OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="NewarkTournamentTeleOp", group="Linear Opmode")

//TELEOP KEY

/**
 * Gamepad1:
 *      sticks move the robot                   -
 *      left bumper makes robot slower          -
 *      right bumper makes robot faster         -
 *      button A resets the encoder values      -
 *      button Y moves armMotor back            -
 *      button X moves armMotor forward         -
 *      dpad up makes foundation movers open    -
 *      dpad down makes foundation movers close -
 *
 * Gamepad2:
 *      button Y makes claw servo close
 *      button X makes claw servos open
 *      button A moves chicken wing down        -
 *      button B moves chicken wing up          -
 *      dpad up moves lift motors up            -
 *      dpad down moves lift motors down        -
 */

public class ScrimmageTeleOp extends LinearOpMode {
    Robot robot = new Robot();

    double coeff = 0.6;

    @Override
    public void runOpMode() {
        robot.init(hardwareMap);
        telemetry.addData("Status", "Initialized");
        telemetry.update();

        double speedLF;
        double speedRF;
        double speedLB;
        double speedRB;

        //rightDrive.setDirection(DcMotor.Direction.REVERSE);

        // Wait for the game to start (driver presses PLAY)
        waitForStart();
        //robot.runtime.reset();

        // run until the end of the match (driver presses STOP)
        while (opModeIsActive()) {

            // Choose to drive using either Tank Mode, or POV Mode
            // Comment out the method that's not used.  The default below is POV.

            // POV Mode uses left stick to go forward, and right stick to turn.
            // - This uses basic math to combine motions and is easier to drive straight.
            speedLF = coeff * -(gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
            speedLB = coeff * -(gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
            speedRF = coeff * -(gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
            speedRB = coeff * -(gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);

            double coeff2 = -0.2;

            if (gamepad1.left_bumper) {
                speedLF = coeff2 * (gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
                speedLB = coeff2 * (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
                speedRF = coeff2 * (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
                speedRB = coeff2 * (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            }

            double coeff3 = -1;

            if (gamepad1.right_bumper) {
                speedLF = coeff3 * (gamepad1.left_stick_y + gamepad1.left_stick_x - gamepad1.right_stick_x);
                speedLB = coeff3 * (gamepad1.left_stick_y - gamepad1.left_stick_x - gamepad1.right_stick_x);
                speedRF = coeff3 * (gamepad1.left_stick_y - gamepad1.left_stick_x + gamepad1.right_stick_x);
                speedRB = coeff3 * (gamepad1.left_stick_y + gamepad1.left_stick_x + gamepad1.right_stick_x);
            }


            if (gamepad1.a) {
                robot.leftFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.rightFrontDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.leftBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
                robot.rightBackDrive.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

                robot.leftFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightFrontDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.leftBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
                robot.rightBackDrive.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
            }


            //PUT OLD CLAW POSITIONS BACK IN

            if (gamepad2.x) {
                robot.rightClaw.setPosition(-1);
                robot.leftClaw.setPosition(1);
            }
            if (gamepad2.y) {
                robot.rightClaw.setPosition(1);
                robot.leftClaw.setPosition(-1);
            }

//            if (gamepad2.x) {
//                robot.rightClaw.setPosition(.2);
//            }
//            if (gamepad2.y) {
//                robot.rightClaw.setPosition(.7);
//            }
//
//            if (gamepad2.a){
//                robot.leftClaw.setPosition(.2);
//            }
//
//            if (gamepad2.b){
//                robot.leftClaw.setPosition(.7);
//            }

//            if (gamepad1.x)
//            {
//                robot.leftServo.setPosition(1);
//                robot.rightServo.setPosition(-1);
//            }
//            if (gamepad1.y) {
//                robot.leftServo.setPosition(-1);
//                robot.rightServo.setPosition(1);
//            }

            if (gamepad1.dpad_up){
                robot.FMRight.setPosition(1);
                robot.FMLeft.setPosition(-1);
            }

            if (gamepad1.dpad_down){
                robot.FMRight.setPosition(-1);
                robot.FMLeft.setPosition(1);
            }

            //PUT CHICKEN WING BACK IN

            if (gamepad2.a){
                robot.chickenServo.setPosition(1);
                //position 1 is down
            }

            if (gamepad2.b){
                robot.chickenServo.setPosition(-1);
                //position -1 is up
            }

            if (gamepad2.dpad_up) {
                robot.topLiftMotor.setPower(-.75);
                robot.bottomLiftMotor.setPower(.75);
            }


            if (gamepad2.dpad_down) {
                robot.topLiftMotor.setPower(.8);
                robot.bottomLiftMotor.setPower(-.8);
            }

            if (!gamepad2.dpad_up && !gamepad2.dpad_down) {
                robot.topLiftMotor.setPower(0);
                robot.bottomLiftMotor.setPower(0);
            }





//            if (gamepad1.dpad_up){
//                liftPower = 1;
//            }
//            else
//                liftPower = 0;
//            if (gamepad1.dpad_down) {
//                liftPower = -1;
//            }
//
//            if (gamepad2.dpad_up)
//            {
//                robot.leftServo.setPosition(1);
//            }
//            else
//                cascadingPower = 0;

//            if (gamepad2.dpad_down)
//            {
//                cascadingPower = -1;
//            }
//
//            if (gamepad2.dpad_right)
//            {
//                anglePower = 1;
//            }
//            else
//                anglePower = 0;
//            if (gamepad2.dpad_left)
//            {
//                anglePower = -1;
//            }

//            robot.leftFrontDrive.setPower(speedLF);
//            robot.leftBackDrive.setPower(speedLB);
//            robot.rightFrontDrive.setPower(speedRF);
//            robot.rightBackDrive.setPower(speedRB);

            robot.leftFrontDrive.setPower(speedRF);
            robot.leftBackDrive.setPower(speedRB);
            robot.rightFrontDrive.setPower(speedLF);
            robot.rightBackDrive.setPower(speedLB);

//            robot.cascadingMotor.setPower(cascadingPower);
//            robot.angleMotor.setPower(anglePower);
            //robot.liftMotor.setPower(liftPower);

            // Show the elapsed game time and wheel power.
            //telemetry.addData("Status", "Run Time: " + robot.runtime.toString());
            telemetry.addData("Motors", "left (%.2f), right (%.2f)", speedLB, speedRB);
            telemetry.addData("LFP, RFP", "Running at %7d :%7d",
                    robot.leftFrontDrive.getCurrentPosition(),
                    robot.rightFrontDrive.getCurrentPosition());
            telemetry.addData("LBP, RBP", "Running at %7d :%7d",
                    robot.leftBackDrive.getCurrentPosition(),
                    robot.rightBackDrive.getCurrentPosition());
            telemetry.update();
        }
    }
}

