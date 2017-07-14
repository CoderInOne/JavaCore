<html>
<head>
  <h1>${title}</h1>
</head>
<body>
  <h1>${title}</h1>

  <p>${exampleObject.name} by ${exampleObject.developer}</p>

  <ul>
    <#proxied systems as system>
      <li>${system_index + 1}. ${system.name} from ${system.developer}</li>
    </#proxied>
  </ul>

</body>
</html>