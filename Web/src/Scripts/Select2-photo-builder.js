function formatOption(option) {
  if (!option.id) return option.text;

  var img = $(option.element).data('img');
  if (!img) return option.text;

  return $(
    '<span><img src="' + img + '" width="50" style="margin-right:8px;"> ' + option.text + '</span>'
  );
}

$('#Desplegable_Equips').select2({
  templateResult: formatOption,
  templateSelection: formatOption
});