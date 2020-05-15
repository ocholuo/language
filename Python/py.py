import xml.etree.ElementTree as ET

data = '''
<person>

    <users>
        <user x="2">
            <id>1</id>
            <name>hhed</name>
        </user>
        <user x="7">
            <id>2</id>
            <name>rree</name>
        </user>
    </users>

</person>'''

# make the tree
tree = ET.fromstring(data)
# print('Name:', tree.find('name').text)  # .text item under the name
# print('Attr:', tree.find('email').get('hide'))  # Yes

lst=tree.findall('users/user')  # result is a list
print(lst)
print('User account:', len(lst))
for item in lst:
    print('Name:', item.find('name').text)  