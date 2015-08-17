#!/usr/bin/python

import os
import os.path
import git
rootdir = '/home/junhongl/Dev/workspace/openstack_dev'
exclude_dir = ['.metadata', 'RemoteSystemsTempFiles']
failed_dirs = []
success_no = 0
git_dirs = []
total = 0

dirnames = os.listdir(rootdir)
git_dirs = [i for i in dirnames if i not in exclude_dir]
index = 1
total = len(git_dirs)
for dirname in git_dirs:
	try:
		print "-" * 40
		print "Updating %d/%d, project %s" % (index, total, dirname)
		repo = git.Repo(os.path.join(rootdir, dirname))
		repo.git.checkout('master')
		repo.git.pull()
		success_no += 1
		print "DONE!"
	except Exception, e:
		failed_dirs.append(dirname)
		print "Update FAILED!"
	finally:
		index += 1

print '=' * 50
if success_no == total:
	print "Nice! All %d projects get updated successfully!" % total
else:
	print "%d projects get updated, %d failed. The failed projects are:" % (success_no, len(failed_dirs))
	print " ".join(failed_dirs)
