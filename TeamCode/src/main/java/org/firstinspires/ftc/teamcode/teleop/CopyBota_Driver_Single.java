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

package org.firstinspires.ftc.teamcode.teleop;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.control.Controller;
import org.firstinspires.ftc.teamcode.hardware.bots.CopyBotaBot;

/**
 * This file contains an example of an iterative (Non-Linear) "OpMode".
 * An OpMode is a 'program' that runs in either the autonomous or the teleop period of an FTC match.
 * The names of OpModes appear on the menu of the FTC Driver Station.
 * When an selection is made from the menu, the corresponding OpMode
 * class is instantiated on the Robot Controller and executed.
 *
 * This particular OpMode just executes a basic Tank Drive Teleop for a two wheeled robot
 * It includes all the skeletal structure that all iterative OpModes contain.
 *
 * Use Android Studios to Copy this Class, and Paste it into your team's code folder with a new name.
 * Remove or comment out the @Disabled line to add this opmode to the Driver Station OpMode list
 */

@TeleOp(name="CopyBota Drive - Single", group="CopyBota")

public class CopyBota_Driver_Single extends OpMode
{
    // Declare OpMode members.
    private ElapsedTime runtime = new ElapsedTime();

    private CopyBotaBot bot = null;

    private boolean gyroMode = false;

    private double Sensitivity = 1.0;
    private Controller controller = null;
    private Controller controller2 = null;
    /*
     * Code to run ONCE when the driver hits INIT
     */
    @Override
    public void init() {
        telemetry.addData("Status", "Initialized");

        bot = new CopyBotaBot(hardwareMap);
        controller = new Controller(gamepad1);
        controller2 = new Controller(gamepad2);
        bot.Init();

    }



    /*
     * Code to run REPEATEDLY after the driver hits INIT, but before they hit PLAY
     */
    @Override
    public void init_loop() {
        telemetry.addData("Status", "Initialized. Gyro Calibration");
    }

    /*
     * Code to run ONCE when the driver hits PLAY
     */
    @Override
    public void start() {

        runtime.reset();
    }

    /*
     * Code to run REPEATEDLY after the driver hits PLAY but before they hit STOP
     */
    double liftVal = 0;
    double pushVal = 0;
    @Override
    public void loop() {

        controller.Update();
        controller2.Update();

        telemetry.addData("Status", "Run Time: " + runtime.toString());
        telemetry.addData("GyroMode", gyroMode);
        telemetry.addData("Sensitivity",  Sensitivity);

        bot.Drive(-gamepad1.left_stick_x, -gamepad1.left_stick_y,gamepad1.right_stick_x, Sensitivity);

        bot.Help_Motor.setPower(gamepad1.left_trigger);


        if(gamepad1.right_trigger > 0.3){
            liftVal = 1;
        }else if(gamepad1.left_trigger > 0.3){
            liftVal = -1;
        }else{
            liftVal = 0;
        }

        bot.LiftOverride(liftVal);

        if(controller.DPadUp == Controller.ButtonState.JUST_PRESSED){
            Sensitivity += 0.15;
        }
        if(controller.DPadDown == Controller.ButtonState.JUST_PRESSED){
            Sensitivity -= 0.15;
        }
        if(controller.AState == Controller.ButtonState.JUST_PRESSED){
            bot.CloseGrab();
        }
        if(controller.BState == Controller.ButtonState.JUST_PRESSED){
            bot.OpenGrab();
        }
        if(controller.XState == Controller.ButtonState.JUST_PRESSED){
            bot.MiddleGrab();
        }



    }

    /*
     * Code to run ONCE after the driver hits STOP
     */
    @Override
    public void stop() {

    }

}
