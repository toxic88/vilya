//
// $Id$

package com.threerings.ezgame.data {

import com.threerings.util.Hashable;
import com.threerings.util.MessageBundle;

import com.threerings.io.ObjectInputStream;
import com.threerings.io.ObjectOutputStream;

import com.threerings.crowd.client.PlaceController;

import com.threerings.parlor.game.client.GameConfigurator;
import com.threerings.parlor.game.data.GameConfig;

import com.threerings.ezgame.client.EZGameController;

/**
 * A game config for a simple multiplayer ez game.
 */
public class EZGameConfig extends GameConfig
    implements Hashable
{
    // TODO: this will eventually contain various XML configuration bits,
    // or we'll expand this to contain other information.
    // For now, the configData is either a classname or url.
    public var configData :String;

    // TODO: this is separate right now, but may eventually be extracted
    // from configData? Do not read this value, use getGameType()
    public var gameType :int = SEATED_GAME;

    /** If non-zero, a game id used to persistently identify the game.
     * This could be thought of as a new-style rating id. */
    public var persistentGameId:int;

    public function EZGameConfig ()
    {
        // nothing needed
    }

    override public function getGameType () :int
    {
        return gameType;
    }

    // from abstract GameConfig
    override public function getBundleName () :String
    {
        return "general";
    }

    // TODO
    //override public function createConfigurator () :GameConfigurator


    override public function getGameName () :String
    {
        return MessageBundle.taint(configData);
    }

    // from PlaceConfig
    override public function createController () :PlaceController
    {
        return new EZGameController();
    }

    // from abstract PlaceConfig
    override public function getManagerClassName () :String
    {
        throw new Error("Not implemented.");
    }

    override public function hashCode () :int
    {
        return super.hashCode(); // TODO: incorporate configData?
    }

    override public function equals (other :Object) :Boolean
    {
        if (!super.equals(other)) {
            return false;
        }

        var that :EZGameConfig = (other as EZGameConfig);
        return (this.configData === that.configData);
    }

    // from interface Streamable
    override public function readObject (ins :ObjectInputStream) :void
    {
        super.readObject(ins);

        configData = (ins.readField(String) as String);
        gameType = ins.readByte();
        persistentGameId = ins.readInt();
    }

    // from interface Streamable
    override public function writeObject (out :ObjectOutputStream) :void
    {
        super.writeObject(out);

        out.writeField(configData);
        out.writeByte(gameType);
        out.writeInt(persistentGameId);
    }
}
}
