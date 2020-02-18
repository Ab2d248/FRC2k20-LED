/*----------------------------------------------------------------------------*/
/* Copyright (c) 2019 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.AddressableLED;
import edu.wpi.first.wpilibj.AddressableLEDBuffer;
import edu.wpi.first.wpilibj.Timer;

import java.awt.Color;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FlashLED extends SubsystemBase {
  /**
   * Creates a new FlashLED.
   */
  public static AddressableLED led;
  public static AddressableLEDBuffer m_buffer;
  public static Color onColor, offColor;
  
  public FlashLED() {
    led = new AddressableLED(1);
    m_buffer = new AddressableLEDBuffer(178);
    led.setLength(m_buffer.getLength());
    led.start();

    onColor = new Color(0, 0, 0);
    offColor = new Color(240, 100, 0);

    for(int i = 0; i < m_buffer.getLength(); i++)
    {
      m_buffer.setRGB(i, offColor.getRed(), offColor.getGreen(), offColor.getBlue());
    }
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run

    for(int i = 0; i < m_buffer.getLength()-1; i++)
      m_buffer.setRGB(i, onColor.getRed(), onColor.getGreen(), onColor.getBlue());

    led.setData(m_buffer);
    Timer.delay(0.5);

    for(int i = 0; i < m_buffer.getLength()-1; i++)
      m_buffer.setRGB(i, offColor.getRed(), offColor.getGreen(), offColor.getBlue());

    led.setData(m_buffer);
    Timer.delay(0.5);
  }
}
