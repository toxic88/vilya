//
// $Id$
//
// Vilya library - tools for developing networked games
// Copyright (C) 2002-2007 Three Rings Design, Inc., All Rights Reserved
// http://www.threerings.net/code/vilya/
//
// This library is free software; you can redistribute it and/or modify it
// under the terms of the GNU Lesser General Public License as published
// by the Free Software Foundation; either version 2.1 of the License, or
// (at your option) any later version.
//
// This library is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
// Lesser General Public License for more details.
//
// You should have received a copy of the GNU Lesser General Public
// License along with this library; if not, write to the Free Software
// Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA

package com.threerings.parlor.card.trick.client {

import com.threerings.io.TypedArray;
import com.threerings.parlor.card.data.Card;
import com.threerings.presents.client.Client;
import com.threerings.presents.client.InvocationService;

/**
 * An ActionScript version of the Java TrickCardGameService interface.
 */
public interface TrickCardGameService extends InvocationService
{
    // from Java interface TrickCardGameService
    function playCard (arg1 :Client, arg2 :Card, arg3 :int) :void;

    // from Java interface TrickCardGameService
    function requestRematch (arg1 :Client) :void;

    // from Java interface TrickCardGameService
    function sendCardsToPlayer (arg1 :Client, arg2 :int, arg3 :TypedArray /* of class com.threerings.parlor.card.data.Card */) :void;
}
}
