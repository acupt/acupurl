CREATE TABLE `surl` (
  `id` varchar(255) NOT NULL,
  `seq` bigint(20) NOT NULL DEFAULT '0' COMMENT '十进制序号',
  `target_url` varchar(2048) NOT NULL COMMENT '目标网址',
  `created_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '创建时间',
  `invalid_time` bigint(20) NOT NULL DEFAULT '0' COMMENT '失效时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_seq` (`seq`),
  KEY `idx_invalid_time` (`invalid_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;