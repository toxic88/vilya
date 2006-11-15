//
// $Id$

package com.threerings.ezgame.client;

import com.threerings.presents.client.Client;
import com.threerings.presents.client.InvocationService;

/**
 * Provides services for ez games.
 */
public interface EZGameService extends InvocationService
{
    /**
     * Request to set the specified property.
     *
     * @param value either a byte[] if setting a non-array property
     * or a property at an array index, or a byte[][] if setting
     * an array property where index is -1.
     */
    public void setProperty (
        Client client, String propName, Object value, int index,
        InvocationListener listener);

    /**
     * Request to end the turn, possibly futzing the next turn holder unless
     * -1 is specified for the nextPlayerIndex.
     */
    public void endTurn (
        Client client, int nextPlayerIndex, InvocationListener listener);

    /**
     * Request to end the game, with the specified player indices assigned
     * as winners.
     */
    public void endGame (
        Client client, int[] winners, InvocationListener listener);

    /**
     * Request to send a private message to one other player in
     * the game.
     *
     * @param value either a byte[] if setting a non-array property
     * or a property at an array index, or a byte[][] if setting
     * an array property where index is -1.
     */
    public void sendMessage (
        Client client, String msgName, Object value, int playerIdx,
        InvocationListener listener);

    /**
     * Add to the specified named collection.
     *
     * @param clearExisting if true, wipe the old contents.
     */
    public void addToCollection (
        Client client, String collName, byte[][] data, boolean clearExisting,
        InvocationListener listener);

    /**
     * Merge the specified collection into the other.
     */
    public void mergeCollection (
        Client client, String srcColl, String intoColl,
        InvocationListener listener);

    /**
     * Pick or deal some number of elements from the specified collection,
     * and either set a property in the flash object, or delivery the
     * picks to the specified player index via a game message.
     */
    public void getFromCollection (
        Client client, String collName, boolean consume, int count,
        String msgOrPropName, int playerIndex, ConfirmListener listener);

    /**
     * Start a ticker that will send out timestamp information at
     * the interval specified.
     *
     * @param msOfDelay must be at least 50, or 0 may be set to halt
     * and clear a previously started ticker.
     */
    public void setTicker (
        Client client, String tickerName, int msOfDelay,
        InvocationListener listener);

    /**
     * Request to get the specified user's cookie.
     */
    public void getCookie (
        Client client, int playerIndex, InvocationListener listener);

    /**
     * Request to set our cookie.
     */
    public void setCookie (
        Client client, byte[] cookie, InvocationListener listener);
}
