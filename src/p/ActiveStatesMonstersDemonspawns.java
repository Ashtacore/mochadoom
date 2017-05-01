/*
 * Copyright (C) 1993-1996 by id Software, Inc.
 * Copyright (C) 2017 Good Sign
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package p;

import data.mobjtype_t;
import data.sounds;
import p.ActionSystem.AbstractCommand;

interface ActiveStatesMonstersDemonspawns<R extends Actions.Registry & AbstractCommand<R>> extends ActiveStatesAi<R>, ActionsMissiles<R> {
    //
    // A_TroopAttack
    //
    default void A_TroopAttack(mobj_t actor) {
        final Actions.Registry obs = obs();
        int damage;

        if (actor.target == null) {
            return;
        }

        A_FaceTarget(actor);
        if (obs.EN.CheckMeleeRange(actor)) {
            obs.DOOM.doomSound.StartSound(actor, sounds.sfxenum_t.sfx_claw);
            damage = (obs.DOOM.random.P_Random() % 8 + 1) * 3;
            DamageMobj(actor.target, actor, actor, damage);
            return;
        }

        // launch a missile
        SpawnMissile(actor, actor.target, mobjtype_t.MT_TROOPSHOT);
    }

    default void A_SargAttack(mobj_t actor) {
        final Actions.Registry obs = obs();
        int damage;

        if (actor.target == null) {
            return;
        }

        A_FaceTarget(actor);
        if (obs.EN.CheckMeleeRange(actor)) {
            damage = ((obs.DOOM.random.P_Random() % 10) + 1) * 4;
            DamageMobj(actor.target, actor, actor, damage);
        }
    }

    default void A_HeadAttack(mobj_t actor) {
        final Actions.Registry obs = obs();
        int damage;

        if (actor.target == null) {
            return;
        }

        A_FaceTarget(actor);
        if (obs.EN.CheckMeleeRange(actor)) {
            damage = (obs.DOOM.random.P_Random() % 6 + 1) * 10;
            DamageMobj(actor.target, actor, actor, damage);
            return;
        }

        // launch a missile
        SpawnMissile(actor, actor.target, mobjtype_t.MT_HEADSHOT);
    }

    default void A_CyberAttack(mobj_t actor) {
        if (actor.target == null) {
            return;
        }

        A_FaceTarget(actor);
        SpawnMissile(actor, actor.target, mobjtype_t.MT_ROCKET);
    }

    default void A_BruisAttack(mobj_t actor) {
        final Actions.Registry obs = obs();
        int damage;

        if (actor.target == null) {
            return;
        }

        if (obs.EN.CheckMeleeRange(actor)) {
            obs.DOOM.doomSound.StartSound(actor, sounds.sfxenum_t.sfx_claw);
            damage = (obs.DOOM.random.P_Random() % 8 + 1) * 10;
            DamageMobj(actor.target, actor, actor, damage);
            return;
        }

        // launch a missile
        SpawnMissile(actor, actor.target, mobjtype_t.MT_BRUISERSHOT);
    }
}
