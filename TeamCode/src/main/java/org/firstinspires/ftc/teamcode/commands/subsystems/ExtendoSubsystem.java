package org.firstinspires.ftc.teamcode.commands.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

/**
 * This class represents the ExtendoSubsystem, responsible for controlling
 * two motors (extendoL and extendoR) used in the extension mechanism of the robot.
 * It utilizes encoders to stop at a specific position when extending.
 */
public class ExtendoSubsystem extends SubsystemBase {

    // Motors for the extension mechanism
    private final DcMotor extendoL;
    private final DcMotor extendoR;

    // Encoder tick count limit for stopping extension
    private final int tickStop;

    /**
     * Constructor to initialize the ExtendoSubsystem with motor configurations.
     *
     * @param hardwareMap the hardware map to access and initialize hardware devices
     */
    public ExtendoSubsystem(HardwareMap hardwareMap) {
        // Initialize motors from the hardware map
        extendoL = hardwareMap.dcMotor.get("extendoLeft");
        extendoR = hardwareMap.dcMotor.get("extendoRight");

        // Set the left motor direction to reverse to match right motor's direction
        extendoL.setDirection(DcMotorSimple.Direction.REVERSE);

        // Set both motors to run using encoders
        extendoL.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        extendoR.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        // Configure motors to brake when power is set to zero
        extendoL.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        extendoR.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        // Set the stopping point for the extension in encoder ticks
        tickStop = 1950;
    }

    /**
     * Method to control the extension movement based on the provided power value.
     * Prevents extension beyond a predefined encoder tick limit.
     *
     * @param value the power level to set for the motors, where positive is extend and negative is retract
     */
    public void move(double value) {
        // Check if we can extend or need to stop based on tickStop limit
        if (value < 0 || extendoL.getCurrentPosition() < tickStop) {
            // Apply power to both motors for movement
            extendoL.setPower(value);
            extendoR.setPower(value);
        } else {
            // Stop motors if tick limit is reached
            extendoL.setPower(0);
            extendoR.setPower(0);
        }
    }

    /**
     * Method to get the current position of the left motor's encoder.
     * Useful for monitoring the extension length.
     *
     * @return the current encoder position of the left motor
     */
    public double getR() {
        return extendoL.getCurrentPosition();
    }
}
