# overthewire

## list

| No. | Puzzle Name             | State                       | Link                                                                                                                                                                                                                                                                                                                                                                                                 |
|-----|-------------------------| ----------------------------| ---------------------------------------------------------------------|
| 1   | Bandit Level 0          | :heavy_check_mark:Completed | https://overthewire.org/wargames/bandit/bandit0.html                                                                                                                                                                                                                                |
| 2   | Persistent Bugger.      | :x: Not Completed           |

# `bandit0@bandit.labs.overthewire.org -p 2220`

# Solutions

## Bandit Level 0
Level Goal
- log into the game using SSH.  
- The host to which you need to connect is `bandit.labs.overthewire.org`, on port `2220`.
- The username is `bandit0` and the password is `bandit0`.
- Once logged in, go to the Level 1 page to find out how to beat Level 1.

solutions
```py
$ ssh bandit0@bandit.labs.overthewire.org -p 2220
Are you sure you want to continue connecting (yes/no)? yes
bandit0@bandit.labs.overthewire.org's password: bandit0

Linux bandit 4.18.12 x86_64 GNU/Linux              
Welcome to OverTheWire!

bandit0@bandit:~$ exit
logout
```

guide:
- connecting to another computer over the Internet,  to keep your data safe. `SSH` is one way to help do that.
- need to `set up SSH` properly on your computer
- and then `create an encrypted connection to your server`.
- for the connection to be secure, both ends of the connection need to `have SSH enabled`.

### 1. Connecting for the First Time
####    1. `Install SSH`
- Windows:
  - download and install an `SSH client program`. The most popular one is `Cygwin`, free from the developer’s website. Download and install it like you would any other program. Another popular free program is `PuTTY`.
  - During the Cygwin installation, you must choose to `install OpenSSH` from the Net section.
  - Windows 10: install the Windows Subsystem for Linux which comes with SSH preinstalled.
- Linux and Mac OS X: come with SSH already installed on the system. This is because SSH is a UNIX system, and Linux and OS X are derived from UNIX.

####    2. `Run SSH`
- Open the terminal program that is installed by Cygwin, Bash on Ubuntu / Win, Terminal in OS X / Linux.
- SSH uses the terminal interface to interact with other computers. no graphical interface for SSH.
####    3. `Test the connection`
- Before dive into creating secure keys and moving files, test that SSH is properly configured on your computer as well as the system you are connecting to.
- Enter command: $ `ssh <username>@<remote>`
  - <username>: your username on the remote computer,
  - <remote>: the address for the remote computer/ server
  - to specify a port: add `-p 0000(port number)`
  - You will be asked for your password once the connection is established. You will not see the cursor move or any characters input when you type your password.
  - If this step fails:
    - SSH is configured incorrectly on your computer
    - the remote computer is not accepting SSH connections.

### 2. Basic Commands
####    1. Navigate the SSH shell `cd command`
- When first connect to the remote computer, should be in your `HOME directory`.
- To move around the directory structure: `cd` command:
  - `cd ..`: move you up one directory.
  - `cd <directoryname>`: into the specified subdirectory.
  - `cd /home/directory/path/`: to the specified directory from the root (home).
  - `cd ~`: return to HOME directory.
####    2. Check your current directory's contents. `ls command`
- To see what files and folders in your current location: `ls` command
  - `ls`: list all of the files and folders in current directory.
  - `ls –l`: list the contents of the directory along with additional information, such as size, permissions, and date.
  - `ls -a`: list all the contents including hidden files and folders.

####    3. Copy files between pc `scp command`
```py
# copy files between local computer and computer accessing remotely
# copy example1.txt to the specified <path> on the remote computer
$ scp /localdirectory/example1.txt <username>@<remote>:<path>
- leave <path> blank: copy to the root folder of the remote computer.

# move example1.txt from the home directory on the remote computer to the current directory on the local computer.
$ scp <username>@<remote>:/home/example1.txt ./
```
####    4. Copy files. `cp command`
- to make copies of files either in the same directory or into a directory of your choosing:
- `cp example1.txt example2.txt`: create a copy of example1.txt called example2.txt in the same location.
- `cp example1.txt <directory>/`: create a copy of example1.txt in the location specified by <directory>.
####    5. Move and rename files. `mv command`
- to change file’s name / move it not copying.
- `mv example1.txt example2.txt`: rename example1.txt to example2.txt. The file will stay in the same location.
- `mv directory1 directory2`: rename directory1 to directory2. The directory’s contents will remain unchanged.
- `mv example1.txt directory2/`: move txt to directory2.
- `mv example1.txt directory2/example2.txt`: move txt to directory2 and rename it to example2.txt

####    6. Delete files and directories. `rm command`
- to remove anything from the computer you are connected to.
- `rm example1.txt`: delete the file example1.txt.
- `rm –I example1.txt`: delete the file example1.txt after prompting you to confirm.
- `rm directory1/`: delete directory1 and all of its contents.
####    7. Change permissions for your files. `chmod`
- You can change the read and write
- `chmod u+w example1.txt`: add the write (modify) permission to the file for the user (u), g for group permissions, o for world permissions.
- `chmod g+r example1.txt`: add the read (access) permission to the file for the group.
####    8. other Commands
- `mkdir newdirectory`: create new subdirectory called newdirectory.
- `pwd`: display your current directory location.
- `who`: shows who is logged into the system.
- `pico newfile.txt` or `vi newfile.txt`: create a new file and open the file editor. Different system will have different file editors installed. may need different commands for different file editor installed.
- `man <command>`: display info about that command.
- `man –k <keyword>`: search all of the man pages for the keyword you specify.

### 3. Creating Encrypted Keys
#### 1. Create your SSH keys.
- These keys will allow you to connect to the remote location without enter your password each time. a much more secure way to connect to the remote computer, as the password will not have to transmitted over the network.
```py
# 1. Create the key folder on your computer:
$ mkdir .ssh
# 2. Create the public and private keys:
$ ssh-keygen –t rsa
  - You will be asked if you would like to create a passphrase for the keys (optional).
  - If you don’t want to create a passphrase, press Enter.
  - This will create two keys in the .ssh directory: *privatekey* `id_rsa` and *publickey* `id_rsa.pub`
# Change your private key’s permissions. to ensure that the private key is only readable by you, enter the command
$ chmod 600 .ssh/id_rsa
```

#### 2. Place the public key on the remote computer.
- Once your keys are created, you’re ready to `place the public key on the remote computer` to connect without a password.
```py
# replacing the appropriate parts as explained earlier:
$ scp .ssh/id_rsa.pub <username>@<remote>:
$ scp .ssh/id_rsa.pub <wiki@shell.cjb.net>:
# Make sure include the colon (:) at the end.
# will be asked to input your password before the file transfer starts.
```

#### 3. Install the public key on the remote computer.
- Once placed the key on the remote computer, need to install it to work correctly.
```py
# 1. log in to the remote computer
$ ssh wiki@shell.cjb.net
# 2. Create an SSH folder on the remote computer, if not already exist
$ mkdir .ssh
# 3. Append your key to the authorized keys file. If the file does not exist yet, it will be created:
$ cat id_rsa.pub >> .ssh/authorized_keys
# 4. Change the permissions for the SSH folder to allow access:
$ chmod 700 .ssh
```

#### 4. Check that the connection works.
- Once the key has been installed on the remote computer, you should be able to initiate a connection without being asked to enter your password.
```py
# 1. to test the connection:
$ ssh <username>@<remote>
# If you connect without being prompted for the password, then the keys are configured
```

## Bandit Level 0 → Level 1
Level Goal
- The password for the next level is stored in a file called `readme` located in the `home directory`.
- Use this password to `log into bandit1` using SSH.
- Whenever you find a password for a level, use SSH (on port 2220) to log into that level and continue the game.
- Commands you may need to solve this level
ls, cd, cat, file, du, find

solution
```py
$ ssh bandit0@bandit.labs.overthewire.org -p 2220
Are you sure you want to continue connecting (yes/no)? yes
bandit0@bandit.labs.overthewire.org's password: bandit0
# Linux bandit 4.18.12 x86_64 GNU/Linux              
# Welcome to OverTheWire!
bandit0@melinda:~$ ls
readme
bandit0@melinda:~$ cat readme
boJ9jbbUNNfktd78OOpsqOltutMc3MY1

$ ssh bandit1@bandit.labs.overthewire.org -p 2220
bandit1@bandit.labs.overthewire.org's password:
boJ9jbbUNNfktd78OOpsqOltutMc3MY1
```


## Bandit Level 1 → Level 2
Level Goal
- The password for the next level is stored in a file called - located in the home directory

solution
```py
bandit1@melinda:~$ ls -a
-  .  ..  .bash_logout  .bashrc  .profile
bandit1@melinda:~$ cat ./-
CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9
```

## Bandit Level 2 → Level 3
Level Goal
- The password for the next level is stored in a file called spaces in this filename located in the home directory

solution
```py
$ ssh bandit2@bandit.labs.overthewire.org -p 2220
bandit2@bandit.labs.overthewire.org's password:
CV1DtqXWVFXTvM2F0k09SHz0YwRINYA9

bandit2@melinda:~$ dir
spaces\ in\ this\ filename
bandit1@melinda:~$ cat spaces\ in\ this\ filename
UmHadQclWmgdLOKQ3YNgjWxGoRMb5luK
```

## Bandit Level 3 → Level 4 `hidden file: ls -a`
Level Goal
- The password for the next level is stored in a hidden file in the inhere directory.

solution
```py
bandit3@melinda:~$ ls
inhere
bandit3@bandit:~$ cat inhere
cat: inhere: Is a directory     # not file
bandit3@melinda:~$ cd inhere
bandit3@melinda:~/inhere$ ls -a
.  ..  .hidden
bandit3@melinda:~/inhere$ cat .hidden
pIwrPrtPN36QITSp3EQaw936yaFoFgAB
```

## Bandit Level 4 → Level 5 `human-readable: find ./-*`
Level Goal
- The password for the next level is stored in the only human-readable file in the inhere directory. Tip: if your terminal is messed up, try the “reset” command.

solution
```py
# file ./-*
# ./-* this shell glob will pick up the all files which starts from - (minus sign) and print the file format of the file.

bandit4@melinda:~$ ls -a
.  ..  .bash_logout  .bashrc  .profile  inhere
bandit4@melinda:~$ cd inhere
bandit4@melinda:~/inhere$ ls -a
-file00  -file02  -file04  -file06  -file08  .
-file01  -file03  -file05  -file07  -file09  ..
bandit4@melinda:~/inhere$ file ./-*
./-file00: data
./-file01: data
./-file02: data
./-file03: data
./-file04: data
./-file05: data
./-file06: data
./-file07: ASCII text
./-file08: data
./-file09: data
bandit4@melinda:~/inhere$ cat ./-file07
koReBOKuIDDepwhWk7jZC0RTdopnAYKh
```

## Bandit Level 5 → Level 6 `following properties: find itype -size`
Level Goal
- The password for the next level is stored in a file somewhere under the inhere directory and has all of the following properties:
- human-readable
- 1033 bytes in size
- not executable

solution
```py
bandit5@melinda:~$ ls
inhere
bandit5@melinda:~$ cd inhere
bandit5@melinda:~/inhere$ ls -a
.            maybehere02  maybehere06  maybehere10  maybehere14  maybehere18
..           maybehere03  maybehere07  maybehere11  maybehere15  maybehere19
maybehere00  maybehere04  maybehere08  maybehere12  maybehere16
maybehere01  maybehere05  maybehere09  maybehere13  maybehere17

bandit5@melinda:~/inhere$ find -type f -size 1033c
./maybehere07/.file2
bandit5@melinda:~/inhere$ cat ./maybehere07/.file2
DXjZPULLxYr17uwoI01bNLQbtFemEgo7
```

## Bandit Level 6 → Level 7 `following properties: find / -user -group -size 2>/dev/null`
Level Goal
- The password for the next level is stored somewhere on the server and has all of the following properties:
- owned by user bandit7
- owned by group bandit6
- 33 bytes in size

solution
```py
$ ssh bandit6@bandit.labs.overthewire.org -p 2220

# “2> /dev/null” 代表忽略掉错误提示信息
# https://www.zhihu.com/question/53295083
bandit6@melinda:~$ find / -user bandit7 -group bandit6 -size 32c 2>/dev/null
/var/lib/dpkg/info/bandit7.password

bandit6@melinda:~$ cat /var/lib/dpkg/info/bandit7.password
HKBPTKQnIay4Fw76bEy8PVxKEDQRKTzs
```

## Bandit Level 7 → Level 8 `in the file data.txt next to the word millionth: awk '/millionth/' data.txt`
Level Goal
- The password for the next level is stored in the file data.txt next to the word `millionth`
```py
bandit7@melinda:~$ ls -a
.  ..  .bash_logout  .bashrc  .profile  data.txt
bandit7@bandit:~$ cat data.txt
humiliation's	47r0YuNylaQ3k6HqGF5NsPPiGuolDCjn
malarkey's	0huyJeRwvtJaoyRmJjQFsRnQcYG4gDir
prioress	ocudTlq9CbpCw9aByrqGffAuoYvCmLNV
....

bandit7@bandit:~$ awk '/millionth/' data.txt
millionth	cvX2JJa4CFALtqS87jk27qwqGhBM9plV
bandit7@melinda:~$ awk '/^millionth/ {print $2;}' data.txt
cvX2JJa4CFALtqS87jk27qwqGhBM9plV
```

## Bandit Level 8 → Level 9 `line of text that occurs only once: sort data.txt | uniq -u`
Level Goal
- The password for the next level is stored in the file data.txt and is the only line of text that occurs only once

solution
```py
bandit8@bandit:~$ ls -a
.  ..  .bash_logout  .bashrc  data.txt  .profile
bandit8@bandit:~$ cat data.txt
KerqNiDbY0zV2VxnOCmWX5XWxumldlAe
MsxcvOe3PGrt78wpZG2bBNF5wfXpZhET
...

bandit8@bandit:~$ cat data.txt | sort | uniq -u
UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR
bandit8@bandit:~$ sort data.txt | uniq -u
UsvVyFSfZZWbi6wgC7dAFyFuR6jQQUhR
```

## Bandit Level 9 → Level 10
Level Goal
- The password for the next level is stored in the file data.txt in one of the few human-readable strings, beginning with several ‘=’ characters.

solution
```py
bandit9@melinda:~$ ls -a
.  ..  .bash_logout  .bashrc  .profile  data.txt
bandit9@melinda:~$ strings data.txt | grep "="
epr~F=K
========== truKLdjsbJ5g7yyJ2X2R0o3a5HQJFuLk
ie)=5e
```

## Bandit Level 10 → Level 11
Level Goal
- The password for the next level is stored in the file data.txt, which contains base64 encoded data

solution
```py
bandit10@melinda:~$ ls -a
.  ..  .bash_logout  .bashrc  .profile  data.txt
bandit10@melinda:~$ cat data.txt
VGhlIHBhc3N3b3JkIGlzIElGdWt3S0dzRlc4TU9xM0lSRnFyeEUxaHhUTkViVVBSCg==

bandit10@melinda:~$ echo VGhlIHBhc3N3b3JkIGlzIElGdWt3S0dzRlc4TU9xM0lSRnFyeEUxaHhUTkViVVBSCg== | base64 --decode
The password is IFukwKGsFW8MOq3IRFqrxE1hxTNEbUPR
```

---

## Bandit Level 11 → Level 12
Level Goal
- The password for the next level is stored in the file data.txt, where all lowercase (a-z) and uppercase (A-Z) letters have been rotated by 13 positions

Commands used for this level:
`ls` – List information about the FILE’s (the current directory by default).
`cat` – used to view contents of a file.
`tr` – translate or delete characters.

solution
```py
bandit11@bandit:~$ ls -a
.  ..  .bash_logout  .bashrc  data.txt  .profile
bandit11@bandit:~$ cat data.txt
Gur cnffjbeq vf 5Gr8L4qetPEsPk8htqjhRK8XSP6x2RHh

bandit11@bandit:~$ cat data.txt | tr 'A-Z a-z' 'N-ZA-M n-za-m'
The password is 5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu
bandit11@bandit:~$ cat data.txt | tr [A-Za-z] [N-ZA-Mn-za-m]
The password is 5Te8Y4drgCRfCx8ugdwuEX8KFC6k2EUu
```

---

## Bandit Level 12 → Level 13
Level Goal
The password for the next level is stored in the file data.txt, which is a hexdump of a file that has been repeatedly compressed. For this level it may be useful to create a directory under /tmp in which you can work using mkdir. For example: mkdir /tmp/myname123. Then copy the datafile using cp, and rename it using mv (read the manpages!)



solution
```py

```

solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
solution
```py

```
