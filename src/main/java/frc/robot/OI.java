/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018-2019 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import frc.robot.commands.*;
import frc.robot.subsystems.*;

/**
 * Add your docs here.
 */


 
public class OI {

    public Joystick joy;
    public JoystickButton hopperIntake, hopperOutake;

    public SolidLED sl;
    public GradientLED gl;
    public FadedLED fl;
    public FlashLED fsl;
    public HopperLED hl;
    public TwoColorLED tcl;
    public RainbowLED rl;

    public OI()
    {
        joy = new Joystick(0);
        hopperIntake = new JoystickButton(joy, 2);
        hopperOutake = new JoystickButton(joy, 1);
        
        //sl = new SolidLED();
        //fl = new FadedLED();
        //gl = new GradientLED();
        //fsl = new FlashLED();
        //tcl = new TwoColorLED();
        rl = new RainbowLED();

        hopperIntake.whenPressed(new HopperIntake());
        hopperOutake.whenPressed(new HopperOutake());
    }

}
