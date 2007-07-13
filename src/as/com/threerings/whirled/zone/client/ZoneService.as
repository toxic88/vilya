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

package com.threerings.whirled.zone.client {

import flash.utils.ByteArray;
import com.threerings.io.TypedArray;
import com.threerings.crowd.data.PlaceConfig;
import com.threerings.presents.client.Client;
import com.threerings.presents.client.InvocationService;
import com.threerings.presents.client.InvocationService_InvocationListener;
import com.threerings.whirled.data.SceneModel;
import com.threerings.whirled.data.SceneUpdate;
import com.threerings.whirled.zone.client.ZoneService;
import com.threerings.whirled.zone.client.ZoneService_ZoneMoveListener;
import com.threerings.whirled.zone.data.ZoneMarshaller_ZoneMoveMarshaller;
import com.threerings.whirled.zone.data.ZoneSummary;

/**
 * An ActionScript version of the Java ZoneService interface.
 */
public interface ZoneService extends InvocationService
{
    // from Java interface ZoneService
    function moveTo (arg1 :Client, arg2 :int, arg3 :int, arg4 :int, arg5 :ZoneService_ZoneMoveListener) :void;
}
}
