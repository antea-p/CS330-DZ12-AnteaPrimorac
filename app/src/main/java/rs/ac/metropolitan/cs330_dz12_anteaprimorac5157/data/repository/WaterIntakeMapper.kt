package rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.repository

import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.data.db.IntakeEntity
import rs.ac.metropolitan.cs330_dz12_anteaprimorac5157.domen.WaterIntake

class WaterIntakeMapper {
    fun toEntity(water: WaterIntake): IntakeEntity {
        return IntakeEntity(
            id = water.id,
            date = water.date,
            amount = water.amount,
            note = water.note
        )
    }

    fun fromEntity(intakeEntity: IntakeEntity): WaterIntake {
        return WaterIntake(
            id = intakeEntity.id,
            date = intakeEntity.date,
            amount = intakeEntity.amount,
            note = intakeEntity.note
        )
    }
}