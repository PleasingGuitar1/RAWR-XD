package org.firstinspires.ftc.teamcode.auto;

import com.disnodeteam.dogecv.math.Line;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.hardware.bots.CopyBotaBot;
import org.firstinspires.ftc.teamcode.lib.auto.DogeAuto;

/**
 * Created by Victo on 1/5/2018.
 */
@Autonomous(name="Copybota Ghetto", group="RED AUTO")
public class CopyBota_Ghetto extends LinearOpMode{
    CopyBotaBot bot = null;

    @Override
    public void runOpMode() throws InterruptedException {
        bot = new CopyBotaBot(hardwareMap);

        bot.Init();

        waitForStart();

        bot.CloseGrab();
        bot.LiftOverride(1);
        bot.WaitForTime(0.5);
        bot.LiftOverride(0);
        bot.DriveEncoder(0,0.8,0.1,-1500);
        bot.OpenGrab();
        bot.WaitForTime(0.5);
        bot.DriveEncoder(0,0.9,0,100);
        /*
        ElapsedTime time = new ElapsedTime();
        while(time.seconds() < 2.0){
            bot.Drive(0,1,0,0.8);
        }
        */
    }
}
