# Access Control Lists(ACL) in Linux

Access control list (ACL) provides an additional, more flexible permission mechanism for file systems.

It is designed to assist with `UNIX file permissions`.

ACL allows you to give permissions for any user or group to any disc resource.


when a particular user is not a member of group created by you but still you want to give some read or write access
- ACLs are used to make a flexible permission mechanism in Linux.

From Linux man pages, ACLs are used to define more fine-grained `discretionary access rights` for files and directories.

## `setfacl` and `getfacl`
`setfacl` and `getfacl` are used for setting up ACL and showing ACL respectively.


```shell
# setting up ACL and showing ACL respectively.
getfacl test/declarations.h

Output:
# file: test/declarations.h
# owner: mandeep
# group: mandeep
user::rw-
group::rw-
other::r--
```

---

### `setfacl` setting up ACL

![acl1](https://i.imgur.com/YFjYnlw.png)

Observe the difference between output of `getfacl` command before and after setting up ACL permissions using `setfacl` command.
- one extra line added for user mandeep which is highlighted in image above.

```py
1. To add permission

    for user:
    setfacl -m "u:user:permissions" /filepath
    setfacl -m u:mandeep:rwx test/declarations.h
    setfacl -m u:mandeep:r-x test/declarations.h

    for a group:
    setfacl -m "g:group:permissions" /filepath

2. To allow all files or directories to inherit ACL entries from the directory it is within

    setfacl -dm "entry" /dirpath

3. To remove a specific entry

    setfacl -x "entry" /filepath

4. To remove all entries

    setfacl -b /filepath
```

---

### `getfacl` View ACL

```py
1. To show permissions :
    getfacl filename
```    

---

### `setfacl -b` Remove ACL
If you want to remove the set ACL permissions, use setfacl -b

```py
remove set permissions
    setfacl -b /path/file

# result: no particular entry for user mandeep in later output.
```

---

## `ls -ltr` check any extra permissions

check if there are any extra permissions set through ACL

![acl4](https://i.imgur.com/3kRye6s.png)

- extra “+” sign after the permissions like `-rw-rwxr–+`: there are extra ACL permissions, check by `getfacl` command.

---

### `setfacl -d` Using Default ACL :
The default ACL
- a specific type of permission assigned to a directory
- doesn’t change the permissions of the directory itself
- but makes that specified ACLs set by default on all the files created inside.

to create a directory and assign default ACL to it by using the `-d` option:

$ mkdir test && setfacl -d -m u:dummy:rw test



















.
