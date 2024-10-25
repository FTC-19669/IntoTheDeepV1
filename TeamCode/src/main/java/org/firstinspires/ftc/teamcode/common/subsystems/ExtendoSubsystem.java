
package org.firstinspires.ftc.teamcode.common.subsystems;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class ExtendoSubsystem extends SubsystemBase {
    private final DcMotor extendoL;
    private final DcMotor extendoR;
    private final int tickStop;

    public ExtendoSubsystem(HardwareMap hardwareMap) {
        extendoL = hardwareMap.dcMotor.get("extendoLeft");
        extendoR = hardwareMap.dcMotor.get("extendoRight");
        extendoL.setDirection(DcMotorSimple.Direction.REVERSE);
        extendoR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendoL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendoL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendoL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        tickStop = 1950;
    }
    public void move(double value) {
        if (value < 0 || extendoL.getCurrentPosition() < tickStop) {
            extendoL.setPower(value);
            extendoR.setPower(value);
        } else {
            extendoL.setPower(0);
            extendoR.setPower(0);
        }
    }
    public double getR() {
        return extendoL.getCurrentPosition();
    }
}
