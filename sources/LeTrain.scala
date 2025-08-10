package myfunlaby

import com.funlabyrinthe.core.*
import com.funlabyrinthe.mazes.*
import com.funlabyrinthe.mazes.std.*

import user.sjrd.railways.*

object LeTrain extends Module:
  override protected def createComponents()(using Universe): Unit =
    val semaphore = new Semaphore
    
    val stopNorth = new StopNorth
    val stopSouth = new StopSouth
    val stopWest = new StopWest
    val stopEast = new StopEast
    
    val greenButton = new GreenButton
    val redButton = new RedButton
    
    val changeArrow1South = new ChangeArrow1South
    val changeArrow1East = new ChangeArrow1East
    val changeArrow2South = new ChangeArrow2South
    val changeArrow2East = new ChangeArrow2East
  end createComponents

  def semaphore(using Universe): Semaphore = myComponentByID("semaphore")
  
  def stopNorth(using Universe): StopNorth = myComponentByID("stopNorth")
  def stopSouth(using Universe): StopSouth = myComponentByID("stopSouth")
  def stopWest(using Universe): StopWest = myComponentByID("stopWest")
  def stopEast(using Universe): StopEast = myComponentByID("stopEast")
  
  def greenButton(using Universe): GreenButton = myComponentByID("greenButton")
  def redButton(using Universe): RedButton = myComponentByID("redButton")
  
  def changeArrow1South(using Universe): ChangeArrow1South = myComponentByID("changeArrow1South")
  def changeArrow1East(using Universe): ChangeArrow2East = myComponentByID("changeArrow1East")
  def changeArrow2South(using Universe): ChangeArrow2South = myComponentByID("changeArrow2South")
  def changeArrow2East(using Universe): ChangeArrow2East = myComponentByID("changeArrow2East")
end LeTrain

export LeTrain.*

class Semaphore(using ComponentInit) extends Switch:
  isOn = true

  override def switchOff(context: MoveContext): Unit = {
    import context.*

    pos.map(0, 17, 0) += railsCreator.createdComponentWithID("rails10")
  }

  override def switchOn(context: MoveContext): Unit = {
    import context.*

    pos.map(0, 17, 0) += railsCreator.createdComponentWithID("rails9")
  }
end Semaphore

class StopNorth(using ComponentInit) extends PushButton:
  editVisualTag = "N"
  
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    enabled = false
    pos.map(11, 0, 0) += railsCreator.createdComponentWithID("rails11")
  }
end StopNorth

class StopSouth(using ComponentInit) extends PushButton:
  editVisualTag = "S"
  
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    enabled = false
    pos.map(15, 26, 0) += railsCreator.createdComponentWithID("rails11")
  }
end StopSouth

class StopWest(using ComponentInit) extends PushButton:
  editVisualTag = "W"
  
  override def buttonDown(context: MoveContext): Unit = {
    import context.*

    enabled = false
    pos.map(0, 15, 0) += railsCreator.createdComponentWithID("rails12")
  }
end StopWest

class StopEast(using ComponentInit) extends PushButton:
  editVisualTag = "E"

  override def buttonDown(context: MoveContext): Unit = {
    import context.*
    
    enabled = false
    pos.map(26, 11, 0) += railsCreator.createdComponentWithID("rails12")
    pos.map(11, 25, 0) += noEffect
    pos.map(13, 25, 0) += westArrow
    pos.map(14, 1, 0) += crossroads
  }
end StopEast

class GreenButton(using ComponentInit) extends PushButton:
  painter = painter.empty + "Buttons/GreenButton"
  downPainter = painter.empty + "Buttons/GreenSunkenButton"
  enabled = false
  
  override def buttonDown(context: MoveContext): Unit = {
    import context.*
    enabled = false
    redButton.enabled = true
    pos.map(16, 20, 0) += goldenKey
  }
end GreenButton

class RedButton(using ComponentInit) extends PushButton:
  painter = painter.empty + "Buttons/RedButton"
  downPainter = painter.empty + "Buttons/RedSunkenButton"
  
  override def buttonDown(context: MoveContext): Unit = {
    import context.*
    enabled = false
    greenButton.enabled = true
    pos.map(16, 20, 0) += noTool
  }
end RedButton

class ChangeArrow1South(using ComponentInit) extends Effect:
  editVisualTag = "1🡓"

  override def execute(context: MoveContext): Unit = {
    import context.*
    pos.map(12, 11, 0) += southArrow
  }
end ChangeArrow1South

class ChangeArrow1East(using ComponentInit) extends Effect:
  editVisualTag = "1🡒"

  override def execute(context: MoveContext): Unit = {
    import context.*
    pos.map(12, 11, 0) += eastArrow
  }
end ChangeArrow1East

class ChangeArrow2South(using ComponentInit) extends Effect:
  editVisualTag = "2🡓"

  override def execute(context: MoveContext): Unit = {
    import context.*
    pos.map(16, 11, 0) += southArrow
  }
end ChangeArrow2South

class ChangeArrow2East(using ComponentInit) extends Effect:
  editVisualTag = "2🡒"

  override def execute(context: MoveContext): Unit = {
    import context.*
    pos.map(16, 11, 0) += eastArrow
  }
end ChangeArrow2East