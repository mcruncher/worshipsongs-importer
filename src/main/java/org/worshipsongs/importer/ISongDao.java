package org.worshipsongs.importer;

import java.sql.Connection;

/**
 * Created by pitchumani on 10/30/15.
 */
public interface ISongDao
{
    Song getSongId(Song song);

    boolean insertSong(Song song);
}
