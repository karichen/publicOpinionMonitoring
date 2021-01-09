create database yqjk;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";

--
-- 表的结构 `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `useremail` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `root` varchar(255) DEFAULT 'normal' COMMENT '权限',
  `keywords` varchar(255) DEFAULT '[]'
) ENGINE=MyISAM DEFAULT CHARSET=utf8 COMMENT='用户表';

--
-- 转存表中的数据 `user`
--

INSERT INTO `user` (`id`, `useremail`, `password`, `root`, `keywords`) VALUES
(14, '1580776594@qq.com', '123456', 'normal', '[{\"keywordFile\":\"新基建\",\"keyword\":[{\"content\":\"农业\"}]},{\"keywordFile\":\"娱乐\",\"keyword\":[{\"content\":\"明星\"}]},{\"keywordFile\":\"北京\",\"keyword\":[{\"content\":\"疫情\"}]},{\"keywordFile\":\"隐秘的角落\",\"keyword\":[{\"content\":\"朱朝阳\"}]}]'),
(15, '1260570909@qq.com', '1', 'normal', '[{\"keywordFile\":\"北京\",\"keyword\":[{\"content\":\"212\"}]},{\"keywordFile\":\"李佳琦\",\"keyword\":[{\"content\":\"直播\"}]}]');

--
--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- 在导出的表使用AUTO_INCREMENT
--

--
-- 使用表AUTO_INCREMENT `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=16;
COMMIT;