#!pwsh
Write-Host "Hello World $PID"
for ($i=0; $i -lt $args.count ; $i++) {
   Write-Host "Args $i = $($args[$i])"; 
}