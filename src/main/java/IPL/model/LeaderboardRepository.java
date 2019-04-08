/*
 * Copyright 2006-2018 (c) Care.com, Inc.
 * 1400 Main Street, Waltham, MA, 02451, U.S.A.
 * All rights reserved.
 *
 * This software is the confidential and proprietary information of
 * Care.com, Inc. ("Confidential Information").  You shall not disclose
 * such Confidential Information and shall use it only in accordance with
 * the terms of an agreement between you and CZen.
 */
package IPL.model;

import org.springframework.data.repository.CrudRepository;

/**
 * Created on 4/8/2019 7:35 PM.
 *
 * @author Aayush Shrivastava
 */
public interface LeaderboardRepository extends CrudRepository<Leaderboard, String> {
}
